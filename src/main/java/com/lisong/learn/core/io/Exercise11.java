package com.lisong.learn.core.io;

import com.lisong.learn.core.innerclasses.facade.Event;
import com.lisong.learn.core.innerclasses.facade.EventFactory;
import com.lisong.learn.core.innerclasses.facade.Impl.GreenhouseController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.lisong.learn.core.util.Print.print;

public class Exercise11 {

    private static Map<String,Long> readEvent(File file) {
        Map<String,Long> elist = new HashMap<>();
        try (Scanner scan = new Scanner(new FileReader(file))) {
            while(scan.hasNext())
                elist.put(className(scan.next()), scan.nextLong());
        }catch (IOException e) {
          throw new RuntimeException(e);
        }
        return elist;
    }

    private static String className(String name) {
        return "com.lisong.learn.core.innerclasses.facade.Impl." + name + "Factory";
    }

    public static void main(String[] args) {
        if(args.length == 0) {
            print("Provide the event configuration file!");
            System.exit(0);
        }
        GreenhouseController controller = new GreenhouseController();
        controller.addEvent(controller.new Bell(1000));
        Map<String,Long> elist = readEvent(new File(args[0]));
        List<Event> events = new ArrayList<>();
        try {
            for(Map.Entry<String,Long> event : elist.entrySet()) {
                Class<?> c = Class.forName(event.getKey());
                events.add(((EventFactory)(c.getConstructor().newInstance())).produce(controller, event.getValue()));
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        controller.addEvent(controller.new Restart(2000, events.toArray(new Event[0])));
        controller.addEvent(new GreenhouseController.Terminate(3000));
        controller.run();
    }
}