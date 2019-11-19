package com.lisong.learn.core.innerclasses.facade;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Controller {

    private List<Event> eventList = new LinkedList<>();

    public void addEvent(Event e) {
        eventList.add(e);
    }

    public void run() {
        while(!eventList.isEmpty()) {
            Iterator<Event> it = new LinkedList<>(eventList).iterator();
            while(it.hasNext()) {
               Event e = it.next();
               if(e.ready()) {
                   print(e);
                   e.action();
                   eventList.remove(e);
               }
           }
        }
    }
}
