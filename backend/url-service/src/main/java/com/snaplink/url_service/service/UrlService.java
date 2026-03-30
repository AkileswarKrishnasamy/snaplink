package com.snaplink.url_service.service;

import com.snaplink.url_service.dto.UrlMappingRequestDTO;
import com.snaplink.url_service.dto.UrlMappingResponseDTO;
import com.snaplink.url_service.kafka.RedirectEvent;
import com.snaplink.url_service.mapper.UrlMapper;
import com.snaplink.url_service.model.UrlMapping;
import com.snaplink.url_service.repository.UrlRepository;
import com.snaplink.url_service.utils.EncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final KafkaTemplate<String, RedirectEvent> kafkaTemplate;

    /**
     * URL Shortening Orchestrator.
     * @return UrlMappingResponse
     */
    public UrlMappingResponseDTO shortUrl(UrlMappingRequestDTO urlMappingRequestDTO){
        UrlMapping urlMapping = UrlMapper.requestDTOToEntity(urlMappingRequestDTO);

        String encodedUrl = EncoderUtil.encode(urlMapping.getActualUrl());
        urlMapping.setEncodedUrl(encodedUrl);

        UrlMapping urlMappingDB = urlRepository.save(urlMapping);
        return UrlMapper.entityToResponseDTO(urlMappingDB);
    }

    /**
     * Get actual URL from the Database
     * @return Actual Url
     */
    public String getActualUrl(String encodedUrl){
        UrlMapping urlMapping = urlRepository.findByEncodedUrl(encodedUrl).orElseThrow(() -> new RuntimeException("Url with given encodedurl not found"));
        RedirectEvent redirectEvent = new RedirectEvent();
        redirectEvent.setIp("ip");
        redirectEvent.setTimestamp(Instant.now());
        redirectEvent.setShortCode(encodedUrl);
        redirectEvent.setUserAgent("user-agent");
        kafkaTemplate.send("analytics", redirectEvent);
        return urlMapping.getActualUrl();
    }
}
