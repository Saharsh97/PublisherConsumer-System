package com.scaler.publisherconsumer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
//@NoArgsConstructor
public class Queue {
    private Map<Long, Topic> topics;

    private static Queue INSTANCE = new Queue();
    private Queue(){
        topics = new HashMap<>();
    }
    public static Queue getInstance(){
        return INSTANCE;
    }

    // -------------------------------------------------------//

    public void addTopic(Topic topic){
        topics.put(topic.getId(), topic);
    }

    public void addEventToTopic(Event event, Long topicId){
        Topic topic = topics.get(topicId);
        topic.getEvents().add(event);
    }

    public List<Event> getAllEventsOfATopic(Long topicId){
        return topics.get(topicId).getEvents();
    }
}
