package com.lisong.learn.core.concurrency.simulation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

import static com.lisong.learn.core.util.Print.print;

public class GreenhouseScheduler {
    class GreenhouseTask implements Runnable, Delayed{
        private String name;
        private Runnable runner;
        private long period;
        private long trigger;

        GreenhouseTask(String name, Runnable runner, long delay, long period) {
            this.name = name;
            this.runner = runner;
            this.period = period;
            this.trigger = Instant.now().toEpochMilli() + delay;
            sequence.add(this);
        }

        GreenhouseTask reset() {
            trigger = Instant.now().toEpochMilli() + period;
            return this;
        }

        @Override
        public int compareTo(Delayed o) {
            GreenhouseTask t = (GreenhouseTask)o;
            if(trigger > t.trigger)
                return 1;
            else
                return trigger < t.trigger ? -1 : 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(trigger - Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS);
        }

        @Override
        public void run() {
            if(runner != null)
                runner.run();
        }

        @Override
        public String toString() {
            return "Greenhouse Task: " + name;
        }
    }
    class GreenhouseRepeatRunner implements Runnable {
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()) {
                    GreenhouseTask task = tasks.take();
                    task.run();
                    tasks.offer(task.reset()); // put back task for repeat execution
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class GreenhouseNormalRunner implements Runnable {
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()) {
                    tasks.take().run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private DelayQueue<GreenhouseTask> tasks = new DelayQueue<>();
    private Set<GreenhouseTask> sequence = new HashSet<>();
    private ExecutorService es = Executors.newCachedThreadPool();
    private volatile boolean light = false;
    private volatile boolean water = false;
    private volatile boolean waterMist = false;
    private volatile boolean fans = false;
    private String thermostat = "Day";
    private synchronized String getThermostat() {
        return thermostat;
    }
    private synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

    static class DataPoint {
        final LocalDateTime time;
        final float temperature;
        final float humidity;
        DataPoint(LocalDateTime time, float temperature, float humidity) {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
                    + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }

    private LocalDateTime lastTime = LocalDateTime.now();
    {
        lastTime = lastTime.withMinute(30).withSecond(0).withNano(0);
    }
    private float lastTemp = 65.0f;
    private float lastHumidity = 50.0f;
    private int tempDirection = 1;
    private int humidityDirection = 1;
    private Random rand = new Random(33);
    private List<DataPoint> dataPoints = Collections.synchronizedList(new ArrayList<>());

    public void startRepeat() {
        configRepeatTask();
        es.execute(new GreenhouseRepeatRunner());
    }

    public void startNormal() {
        configNormalTask();
        es.execute(new GreenhouseNormalRunner());
    }

    private void configRepeatTask() {
        tasks.offer(new GreenhouseTask("LightOn", new LightOn(), 0,200));
        tasks.offer(new GreenhouseTask("LightOff", new LightOff(), 0, 400));
        tasks.offer(new GreenhouseTask("WaterOn", new WaterOn(), 0,600));
        tasks.offer(new GreenhouseTask("WaterOff", new WaterOff(), 0, 800));
        tasks.offer(new GreenhouseTask("WaterMistOn", new WaterMistOn(), 0, 700));
        tasks.offer(new GreenhouseTask("WaterMistOff", new WaterMistOff(), 0, 900));
        tasks.offer(new GreenhouseTask("FansOn", new FansOn(), 0, 300));
        tasks.offer(new GreenhouseTask("FansOff", new FansOff(), 0, 500));
        tasks.offer(new GreenhouseTask("CollectionData", new CollectionData(), 500,500));
        tasks.offer(new GreenhouseTask("ThermostatDay", new ThermostatDay(),0,1400));
        tasks.offer(new GreenhouseTask("ThermostatNight", new ThermostatNight(),0,2000));
        tasks.offer(new GreenhouseTask("Bell", new Bell(), 0, 1000));
        tasks.offer(new EndSentinel(5000));
    }

    private void configNormalTask() {
        tasks.offer(new GreenhouseTask("LightOn", new LightOn(), 100,100));
        tasks.offer(new GreenhouseTask("LightOff", new LightOff(), 1500, 1500));
        tasks.offer(new GreenhouseTask("WaterOn", new WaterOn(), 200,200));
        tasks.offer(new GreenhouseTask("WaterOff", new WaterOff(), 1400, 1400));
        tasks.offer(new GreenhouseTask("WaterMistOn", new WaterMistOn(), 300, 300));
        tasks.offer(new GreenhouseTask("WaterMistOff", new WaterMistOff(), 1300, 1300));
        tasks.offer(new GreenhouseTask("FansOn", new FansOn(), 400, 400));
        tasks.offer(new GreenhouseTask("FansOff", new FansOff(), 1200, 1200));
        tasks.offer(new GreenhouseTask("CollectionData", new CollectionData(), 500,500));
        tasks.offer(new GreenhouseTask("ThermostatDay", new ThermostatDay(),0,0));
        tasks.offer(new GreenhouseTask("ThermostatNight", new ThermostatNight(),1600,1600));
        tasks.offer(new GreenhouseTask("Bell", new Bell(), 1000, 1000));
        tasks.offer(new GreenhouseTask("Restart", new Restart(), 2000, 2000));
        tasks.offer(new EndSentinel(8000));
    }

    class LightOn implements Runnable {
        @Override
        public void run() {
            print("Light is on");
            light = true;
        }
    }

    class LightOff implements Runnable {
        @Override
        public void run() {
            print("Light is off");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        @Override
        public void run() {
            print("Greenhouse water is on");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        @Override
        public void run() {
            print("Greenhouse water is off");
            water = false;
        }
    }

    class WaterMistOn implements Runnable {
        @Override
        public void run() {
            print("Water mist generators on");
            waterMist = true;
        }
    }

    class WaterMistOff implements Runnable {
        @Override
        public void run() {
            print("Water mist generators off");
            waterMist = false;
        }
    }

    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            print("Thermostat on day setting");
            setThermostat("Day");
        }
    }

    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            print("Thermostat on night setting");
            setThermostat("Night");
        }
    }

    class FansOn implements Runnable {
        @Override
        public void run() {
            print("Greenhouse fans is on");
            waterMist = true;
        }
    }

    class FansOff implements Runnable {
        @Override
        public void run() {
            print("Greenhouse fans is off");
            waterMist = false;
        }
    }

    class CollectionData implements Runnable {
        @Override
        public void run() {
            print("Collecting data");
            synchronized (GreenhouseScheduler.this) {
                lastTime = lastTime.plusMinutes(30);
                if(rand.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                lastTemp = lastTemp + tempDirection*(1.0f + rand.nextFloat());
                if(rand.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity + humidityDirection*rand.nextFloat();
                dataPoints.add(new DataPoint(lastTime, lastTemp, lastHumidity));
            }
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            print("Bing!");
        }
    }

    class Restart implements Runnable {
        @Override
        public void run() {
            for(GreenhouseTask task : sequence) {
                if(task.getClass() != EndSentinel.class)
                    tasks.offer(task.reset());
            }
            print("**************Restart**************");
        }
    }

    class Terminate implements Runnable {
        @Override
        public void run() {
            print("Terminating!");
            es.shutdownNow();
            new Thread(()->{
                print("**************Report**************");
                for(DataPoint d : dataPoints)
                    print(d);
            }).start();
        }
    }

    class EndSentinel extends GreenhouseTask {
        EndSentinel(long delay) {
            super("Terminate", new Terminate(), delay, delay);
        }
    }

    public static void main(String[] args) {
        new GreenhouseScheduler().startNormal();
    }
}