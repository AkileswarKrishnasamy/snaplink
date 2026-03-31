package com.snaplink.analytics_service.repository;


import com.snaplink.analytics_service.model.Analytics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {
    Page<Analytics> findAnalyticsByShortCode(String shortCode, Pageable pageable);
}
