package com.snaplink.auth_service.repository;

import com.snaplink.auth_service.model.AuthSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthSessionRepository extends JpaRepository<AuthSession, Long> {
}
