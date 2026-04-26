package com.snaplink.analytics_service.service;

import com.snaplink.analytics_service.model.Analytics;
import com.snaplink.analytics_service.repository.AnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public void saveAnalyticsData(Analytics analytics){
        analyticsRepository.save(analytics);
    }

}
