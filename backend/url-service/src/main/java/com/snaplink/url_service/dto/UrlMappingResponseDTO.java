package com.snaplink.url_service.dto;


public record UrlMappingResponseDTO (
        Long userId,
        String shortCode,
        String actualUrl,
        Boolean isDataAnalyticsRequired
){ }
