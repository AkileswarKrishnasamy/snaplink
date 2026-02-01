package com.snaplink.url_service.repository;

import com.snaplink.url_service.model.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<UrlMapping, String> {

    Optional<UrlMapping> findByEncodedUrl(String encodedUrl);
}
