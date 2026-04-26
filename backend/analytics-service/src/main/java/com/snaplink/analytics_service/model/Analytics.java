package com.snaplink.analytics_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@Entity
@Table(name = "analytics")
@AllArgsConstructor
@NoArgsConstructor
public class Analytics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "url_mapping_id")
    private Long urlMappingId;

    @Column(name = "timestamp")
    private Instant timeStamp;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;
}
