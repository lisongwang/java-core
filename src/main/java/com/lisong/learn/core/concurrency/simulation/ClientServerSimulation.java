package com.lisong.learn.core.concurrency.simulation;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;

public class ClientServerSimulation {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        long period = 5000;
        if(args.length > 0)
            period = Long.parseLong(args[0]);
        ServerManager serverManager = new ServerManager(period, es);

        int serverSize = 7;
        if(args.length > 1)
            serverSize = Integer.parseInt(args[1]);
        for(int i = 0; i < serverSize; i++)
            es.execute(new Server(serverManager));

        es.execute(new Listener(serverManager));

        if(args.length > 2) {
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[2]));
        }else {
            print("Press 'Enter' ");
            System.in.read();
        }
        es.shutdownNow();
    }
}

class Client {
    private static int count = 0;
    private final int id = count++;

    /**
     * mock the time that this client needed from server
     */
    private final long occupy;
    public long getOccupy() {
        return occupy;
    }

    public Client(long occupy) {
        this.occupy = occupy;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", occupy=" + occupy +
                '}';
    }
}

class Request {
    private static int count = 0;
    private final int id = count++;
    private Instant instant = Instant.now();
    public Instant getInstant() {
        return instant;
    }

    private Client client;
    public Client getClient() {
        return client;
    }

    public Request appendClient(Client client) {
        this.client = client;
        return this;
    }

    public static Request build() {
        return new Request();
    }
    private Request() {}

    @Override
    public String toString() {
        return "Request: " + id + "[" + client + "]";
    }
}

class RequestLine extends LinkedBlockingDeque<Request> {
    private static int count = 0;
    private final int id = count++;
    public int getId() {
        return id;
    }

    private long processedRequest = 0;

    public synchronized void increment() { processedRequest++; }

    public synchronized long getProcessedRequest() { return processedRequest; }

    private LogQueue logQueue = new LogQueue();

    // this log should be persisted in db or disk file
    public void log(String logRecord) throws InterruptedException {
        logQueue.put(logRecord);
    }
}

class Listener implements Runnable {
    private RequestLine requestLine;
    private ServerManager serverManager;
    public Listener(ServerManager serverManager) {
        this.serverManager = serverManager;
    }

    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public void start() {
        serverManager.registerListener(this);
    }

    private Random rand = new Random(35);

    @Override
    public void run() {
        start();
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
                requestLine.put(Request.build()
                        .appendClient(new Client(rand.nextInt(500))));
            }
        }catch (InterruptedException e) {
            print("Listener interrupted");
        }
        print("Listener stop");
    }
}

class Server implements Runnable {
    private static int count;
    private final int id = count++;
    private boolean online = false;
    private RequestLine requestLine;
    private ServerManager serverManager;
    private int serveCount = 0;
    public Server(ServerManager serverManager) {
        this.serverManager = serverManager;
    }

    public void start() {
        serverManager.registerServer(this);
        online = true;
    }

    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public synchronized void pause() {
        online = false;
        serveCount = 0;
    }

    public synchronized void restart() {
        online = true;
        notifyAll();
    }

    public synchronized int getServeCount() {
        return serveCount;
    }

    @Override
    public void run() {
        start();
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while(!online) {
                        wait();
                    }
                }
                Request request = requestLine.take();
                logRequestWaitTime(request);
                serveRequest(request);
                success(request);
            }
        }catch (InterruptedException e) {
            print("Server " + id + " interrupted");
        }
        print("Server " + id + " stop");
    }

    private void serveRequest(Request request) throws InterruptedException{
        print(this + " serve " + request);
        TimeUnit.MILLISECONDS.sleep(request.getClient().getOccupy());
    }

    private void logRequestWaitTime(Request request) throws InterruptedException {
        // log the request waiting time
        requestLine.log(request + "-" + request.getInstant().toEpochMilli() +
                    "-" + Instant.now().toEpochMilli());
    }

    private synchronized void success(Request request) {
        requestLine.increment();
        if(online) {
            serveCount++;
        }
        // may do other server log with current request
    }

    @Override
    public String toString() {
        return "Server: " + id;
    }
}

class LogQueue extends LinkedBlockingDeque<String> {}

class ServerLog {
    private LogQueue logQueue;
    public ServerLog(LogQueue logQueue) {
        this.logQueue = logQueue;
    }

    public void log(String msg) throws InterruptedException {
        logQueue.put(msg);
    }
}

abstract class Alert implements Comparable<Alert> {
    private AlertPriority priority;
    public Alert(AlertPriority priority) {
        this.priority = priority;
    }
    public abstract String getMessage();
    @Override
    public int compareTo(Alert o) {
        return Integer.compare(o.priority.ordinal(), priority.ordinal());
    }
}

abstract class AlertHandler {
    protected Class<? extends Alert> alertType;
    protected ServerManager serverManager;
    public AlertHandler(Class<? extends Alert> alertType, ServerManager serverManager) {
        this.alertType = alertType;
        this.serverManager = serverManager;
        serverManager.registerAlertHandler(alertType, this);
    }

    abstract public void handler(Alert alert);
}

enum AlertPriority {
    LOW, NORMAL, HIGH, URGENT
}

class UnavailableAlert extends Alert {
    public UnavailableAlert(AlertPriority priority) {
        super(priority);
    }

    public UnavailableAlert() { super(AlertPriority.URGENT); }

    @Override
    public String getMessage() {
        return "Request line totally be blocked!";
    }
}

class UnavailableAlertHandler extends AlertHandler {
    public UnavailableAlertHandler(ServerManager serverManager) {
        super(UnavailableAlert.class, serverManager);
    }

    @Override
    public void handler(Alert alert) {
        print("Handling: " + alert.getMessage());
        // TODO: another choice is to add more requestLine
        serverManager.moreServer();
    }
}

class BusyAlert extends Alert {
    public BusyAlert(AlertPriority priority) {
        super(priority);
    }

    public BusyAlert() { super(AlertPriority.HIGH); }

    @Override
    public String getMessage() {
        return "Request line is busy!";
    }
}

class BusyAlertHandler extends AlertHandler {
    public BusyAlertHandler(ServerManager serverManager) {
        super(BusyAlert.class, serverManager);
    }

    @Override
    public void handler(Alert alert) {
        print("Handling: " + alert.getMessage());
        // add more servers for current requestLine
        serverManager.moreServer();
    }
}

class FreeAlert extends Alert {
    public FreeAlert(AlertPriority priority) {
        super(priority);
    }

    public FreeAlert() { super(AlertPriority.NORMAL); }

    @Override
    public String getMessage() {
        return "Request line is free!";
    }
}

class FreeAlertHandler extends AlertHandler {
    public FreeAlertHandler(ServerManager serverManager) {
        super(FreeAlert.class, serverManager);
    }

    @Override
    public void handler(Alert alert) {
        print("Handling: " + alert.getMessage());
        // pause some servers of current requestLine
        serverManager.lessServer();
    }
}

class EmptyAlert extends Alert {
    public EmptyAlert(AlertPriority priority) {
        super(priority);
    }

    public EmptyAlert() { super(AlertPriority.LOW); }

    @Override
    public String getMessage() {
        return "Request line is empty!";
    }
}

class EmptyAlertHandler extends AlertHandler {
    public EmptyAlertHandler(ServerManager serverManager) {
        super(EmptyAlert.class, serverManager);
    }

    @Override
    public void handler(Alert alert) {
        print("Handling: " + alert.getMessage());
        // keep only 1 server for current requestLine
        while(serverManager.workingServerCount() > 1) {
            serverManager.lessServer();
        }
    }
}

class ReportAlert extends Alert {
    public ReportAlert(AlertPriority priority) {
        super(priority);
    }

    public ReportAlert() { super(AlertPriority.NORMAL); }

    public ReportAlert(String message) {
        this();
        if(message != null)
            this.message = message;
    }

    private String message = "Blank Report!";

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

class ReportAlertHandler extends AlertHandler {
    public ReportAlertHandler(ServerManager serverManager) {
        super(ReportAlert.class, serverManager);
    }

    @Override
    public void handler(Alert alert) {
        print("Report: " + alert.getMessage());
    }
}

interface AlertGenerator {
    Alert[] issueAlert(RequestLine requestLine);
}

class SimpleAlertStrategy implements AlertGenerator {
    private Instant instant = Instant.now();
    private long emptyCount = 0;
    private long requestCount = 0;

    @Override
    public Alert[] issueAlert(RequestLine requestLine) {
        List<Alert> alerts = new ArrayList<>();
        int size = requestLine.size();
        if(size == 0) {
            // we do not want the emptyAlert throw too quickly
            if(++emptyCount%5 == 0) {
                alerts.add(new EmptyAlert());
                emptyCount = 0;
            }else {
                alerts.add(new FreeAlert());
            }
        }else {
            emptyCount = 0;
            if(size < 100)
                alerts.add(new FreeAlert());
            else if(size < 300)
                alerts.add(new BusyAlert());
            else
                alerts.add(new UnavailableAlert());
        }

        Instant now = Instant.now();
        long elapse = Duration.between(instant, now).toMillis();
        long requests = requestLine.getProcessedRequest();
        long diff = requests - requestCount;
        long qps = Math.floorDiv(diff, TimeUnit.SECONDS.convert(elapse, TimeUnit.MILLISECONDS));
        //show how many request have been processed since last monitor point with the report alert
        alerts.add(new ReportAlert("Processed request(s): " + diff + " during " + elapse + "(ms)\t" + qps +
                "(QPS)\tRequest Line: " + requestLine.getId()));

        instant = now;
        requestCount = requests;
        return alerts.toArray(new Alert[0]);
    }
}

class AlertQueue extends PriorityBlockingQueue<Alert> {}

class ServerManager {
    private RequestLine requestLine = new RequestLine();
    private Queue<Server> idleServers = new LinkedList<>();
    private Queue<Server> workingServers = new PriorityQueue<>(Comparator.comparingInt(Server::getServeCount));
    private AlertQueue alertQueue = new AlertQueue();
    private Listener listener;
    private Map<Class<? extends Alert>,AlertHandler> handlers = new ConcurrentHashMap<>();
    private long monitorPeriod;
    private AlertGenerator alertGenerator;
    private ExecutorService es;

    public ServerManager(ExecutorService es) {
        this(10000, es);
    }

    public ServerManager(long monitorPeriod, ExecutorService es) {
        new UnavailableAlertHandler(this);
        new BusyAlertHandler(this);
        new FreeAlertHandler(this);
        new EmptyAlertHandler(this);
        new ReportAlertHandler(this);
        alertGenerator = new SimpleAlertStrategy();
        this.monitorPeriod = monitorPeriod;
        this.es = es;
        this.es.execute(new Monitor());
        this.es.execute(new AlertDispatcher());
    }

    public synchronized void registerServer(Server server) {
        server.setRequestLine(requestLine);
        workingServers.offer(server);
    }

    public synchronized void registerListener(Listener listener) {
        listener.setRequestLine(requestLine);
        this.listener = listener;
    }

    public void registerAlertHandler(Class<? extends Alert> alertType, AlertHandler handler) {
        handlers.put(alertType, handler);
    }

    public synchronized void moreServer() {
        if(idleServers.size() > 0) {
            Server server = idleServers.poll();
            server.restart();
            workingServers.offer(server);
        }else {
            print("We need more servers for request line: " + requestLine.getId());
        }
    }

    public synchronized void lessServer() {
        if(workingServers.size() > 0) {
            Server server = workingServers.poll();
            server.pause();
            idleServers.offer(server);
        }
    }

    public synchronized int workingServerCount() {
        return workingServers.size();
    }

    /**
     * We can apply variant alert strategy in the monitor
     */
    class Monitor implements Runnable {
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()) {
                    TimeUnit.MILLISECONDS.sleep(monitorPeriod);
                    for(Alert alert : alertGenerator.issueAlert(requestLine)) {
                        alertQueue.offer(alert);
                    }
                }
            }catch (InterruptedException e) {
                print("Monitor interrupted");
            }
            print("Monitor stop");
        }
    }

    class AlertDispatcher implements Runnable {
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()) {
                    Alert alert = alertQueue.take();
                    handlers.get(alert.getClass()).handler(alert);
                }
            }catch (InterruptedException e) {
                print("AlertDispatcher interrupted");
            }
            print("AlertDispatcher stop");
        }
    }
}