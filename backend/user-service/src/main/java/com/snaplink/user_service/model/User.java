package com.snaplink.user_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "auth_id", nullable = false, unique = true)
    private Long authId;
}
