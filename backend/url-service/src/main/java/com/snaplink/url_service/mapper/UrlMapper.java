package com.snaplink.url_service.mapper;

import com.snaplink.url_service.dto.UrlMappingRequestDTO;
import com.snaplink.url_service.dto.UrlMappingResponseDTO;
import com.snaplink.url_service.model.UrlMapping;

public class UrlMapper {

    public static UrlMappingResponseDTO entityToResponseDTO(UrlMapping urlMapping){
        return new UrlMappingResponseDTO(urlMapping.getUserId(),
                urlMapping.getEncodedUrl(),
                urlMapping.getActualUrl(),
                urlMapping.getIsDataAnalyticsRequired());
    }

    public static UrlMapping requestDTOToEntity(UrlMappingRequestDTO urlMappingRequestDTO){
        UrlMapping urlMapping = new UrlMapping();
        if(urlMappingRequestDTO.userId() != null)urlMapping.setUserId(urlMappingRequestDTO.userId());
        urlMapping.setActualUrl(urlMappingRequestDTO.url());
        urlMapping.setIsDataAnalyticsRequired(urlMappingRequestDTO.isDataAnalyticsRequired() != null ? urlMappingRequestDTO.isDataAnalyticsRequired() : Boolean.FALSE );
        return urlMapping;
    }

}
