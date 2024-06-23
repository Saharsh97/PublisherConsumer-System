package com.scaler.publisherconsumer.models;

import java.util.Random;

public class Publisher {
    private Long id;
    private String name;

    public Publisher(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public void publish(String message, Long topicId) {
        Event event = createEvent(message);
        Queue.getInstance().addEventToTopic(event, topicId);
    }

    private Event createEvent(String message){
        return Event.builder()
                .id(new Random().nextLong(10000007))
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
