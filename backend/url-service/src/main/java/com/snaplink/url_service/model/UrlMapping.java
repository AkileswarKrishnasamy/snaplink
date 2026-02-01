package com.snaplink.url_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "url")
public class UrlMapping {

    @Id
    private String id;

    private Long userId;

    private String encodedUrl;

    private String actualUrl;

    private Boolean isDataAnalyticsRequired;

}
