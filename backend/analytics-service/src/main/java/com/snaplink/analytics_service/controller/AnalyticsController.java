package com.snaplink.analytics_service.controller;

import com.snaplink.analytics_service.model.Analytics;
import com.snaplink.analytics_service.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<Page<Analytics>> getAnalyticsByShortCode(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                                   @PathVariable String shortCode) {
        Page<Analytics> analyticsList = analyticsService.getAnalyticsDataForShortCode(shortCode, page, size);
        return ResponseEntity.ok(analyticsList);
    }

}
