package com.snaplink.analytics_service.kafka;

import lombok.Data;

import java.time.Instant;

@Data
public class RedirectEvent {
    private String shortCode;
    private Instant timestamp;
    private String ip;
    private String userAgent;
}
