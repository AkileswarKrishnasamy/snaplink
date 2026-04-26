package com.snaplink.analytics_service.repository;


import com.snaplink.analytics_service.model.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {
}