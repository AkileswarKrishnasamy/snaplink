package com.snaplink.analytics_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "analytics")
public class Analytics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "timestamp")
    private Instant timeStamp;

    @Column(name = "ip")
    private String ip;

    @Column(name = "user_agent")
    private String userAgent;
}
