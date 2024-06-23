package com.scaler.publisherconsumer.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Topic {
    private Long id;
    private List<Event> events;
    private List<Consumer> consumers;

    public Topic(Long topicId){
        this.id = topicId;
        this.events = new ArrayList<>();
        this.consumers = new ArrayList<>();
    }

    public void addConsumer(Consumer consumer){
        this.consumers.add(consumer);
    }
}
