package com.scaler.publisherconsumer.models;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// dont create interfaces now.
// just mention.
// ad create a concrete class.
public class Consumer implements Runnable{
    private Long id;
    private String name;
    private Topic subscribedTopic;
    private AtomicInteger offset;

    public Consumer(Long id, String name, Topic topic){
        this.id = id;
        this.name = name;
        this.subscribedTopic = topic;
        this.offset = new AtomicInteger(0);
    }

    public void setOffset(int offset){
        this.offset.set(offset);
    }

    @Override
    public void run() {
        System.out.println(name + " started on " + Thread.currentThread().getName());
        try {
            while (true) {
                synchronized (offset) {
                    Queue queue = Queue.getInstance();
                    List<Event> eventsList = queue.getAllEventsOfATopic(subscribedTopic.getId());
                    while (offset.get() >= eventsList.size()) {
                        Thread.currentThread().sleep(100);
                    }
                    Event consumedEvent = eventsList.get(offset.get());
                    System.out.println(name + " event " + consumedEvent.getMessage() + " processing...");
                    Thread.sleep(2 * 1000);
                    System.out.println(name + " event " + consumedEvent.getMessage() + " done");
                    offset.compareAndSet(offset.get(), offset.get()+1);
                }
            }
        } catch (Exception e){
            System.out.println(name + " crashed");
        }
    }
}
