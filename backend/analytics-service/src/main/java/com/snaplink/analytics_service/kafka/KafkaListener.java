package com.snaplink.analytics_service.kafka;

import com.snaplink.analytics_service.kafka.RedirectEvent;
import org.springframework.stereotype.Component;


@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(containerFactory = "kafkaListenerContainerFactory",
            topics = "analytics", groupId = "group-analytics")
    public void redirectEventListener(RedirectEvent redirectEvent){
        System.out.println(redirectEvent);
    }
}
