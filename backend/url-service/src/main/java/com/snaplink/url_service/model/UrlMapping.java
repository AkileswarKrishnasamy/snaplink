package com.snaplink.url_service.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "url_mapping")
@Entity
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "url_mapping_id")
    private Long urlMappingId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "short_code", nullable = false)
    private String shortCode;

    @Column(name = "actual_url", nullable = false)
    private String actualUrl;

    @Column(name = "is_data_analytics_required")
    private Boolean isDataAnalyticsRequired;

}
