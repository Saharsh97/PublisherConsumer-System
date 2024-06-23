package com.scaler.publisherconsumer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Event {
    private Long id;
    private String message;
    private Long timestamp;
}
