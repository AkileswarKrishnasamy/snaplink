package com.snaplink.url_service.kafka;

import lombok.Data;

import java.time.Instant;

@Data
public class RedirectEvent {
    private Long urlMappingId;
    private String ipAddress;
    private Instant timestamp;
    private String userAgent;
}
