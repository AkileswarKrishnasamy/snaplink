package com.snaplink.url_service.dto;


public record UrlMappingResponseDTO (
        Long userId,
        String encodedUrl,
        String actualUrl,
        Boolean isDataAnalyticsRequired
){ }
