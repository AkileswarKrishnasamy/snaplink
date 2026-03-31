package com.snaplink.url_service.controller;

import com.snaplink.url_service.dto.UrlMappingRequestDTO;
import com.snaplink.url_service.dto.UrlMappingResponseDTO;
import com.snaplink.url_service.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Slf4j
@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlMappingResponseDTO> shortenUrl(@RequestBody UrlMappingRequestDTO urlMappingRequestDTO) {

        log.info("Received a request with User Id: {}", urlMappingRequestDTO.userId());
        UrlMappingResponseDTO urlMappingResponseDTO = urlService.shortUrl(urlMappingRequestDTO);
        log.info("Successfully shortened url with User Id: {}", urlMappingResponseDTO.userId());

        return ResponseEntity.ok(urlMappingResponseDTO);
    }

    @GetMapping("/actual-url/{encodedUrl}")
    public ResponseEntity<String> getActualUrl(HttpServletRequest httpRequest, @PathVariable String encodedUrl){

        log.info("Received encodedUrl: '{}'", encodedUrl);
        String ipAddr = httpRequest.getRemoteAddr();
        String userAgent = httpRequest.getHeader("User-Agent");
        String actualUrl = urlService.getActualUrl(encodedUrl, ipAddr, userAgent);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(actualUrl));
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).headers(headers).build();
    }
}
