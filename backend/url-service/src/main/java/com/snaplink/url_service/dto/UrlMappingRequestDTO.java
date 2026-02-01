package com.snaplink.url_service.dto;

import jakarta.annotation.Nullable;

public record UrlMappingRequestDTO(
        @Nullable Long userId,
        String url,
        @Nullable Boolean isDataAnalyticsRequired
) {
}
