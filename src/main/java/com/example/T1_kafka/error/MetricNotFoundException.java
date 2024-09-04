package com.example.T1_kafka.error;

public class MetricNotFoundException extends RuntimeException{

    public MetricNotFoundException(String message) {
        super(message);
    }
}
