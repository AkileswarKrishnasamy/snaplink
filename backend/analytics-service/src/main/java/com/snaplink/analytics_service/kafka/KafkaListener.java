package com.snaplink.analytics_service.kafka;

import com.snaplink.analytics_service.kafka.RedirectEvent;
import com.snaplink.analytics_service.model.Analytics;
import com.snaplink.analytics_service.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class KafkaListener {

    private final AnalyticsService analyticsService;

    @org.springframework.kafka.annotation.KafkaListener(containerFactory = "kafkaListenerContainerFactory",
            topics = "${spring.kafka.analytics-topic}", groupId = "group-analytics")
    public void redirectEventListener(RedirectEvent redirectEvent){
        Analytics analytics = Analytics.builder()
                .ip(redirectEvent.getIp())
                .shortCode(redirectEvent.getShortCode())
                .userAgent(redirectEvent.getUserAgent())
                .timeStamp(redirectEvent.getTimestamp())
                .build();

        analyticsService.saveAnalyticsData(analytics);
    }
}
