package com.snaplink.user_service.utils;

import java.security.SecureRandom;
import java.util.HexFormat;

public class UsernameGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateWithEmail(String email) {

        int index = email.indexOf('@');
        if (index <= 0) {
            throw new IllegalArgumentException("Invalid email");
        }

        String localPart = email.substring(0, index);

        StringBuilder normalized = new StringBuilder();

        for (char ch : localPart.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                normalized.append(Character.toLowerCase(ch));
            }
        }

        byte[] bytes = new byte[3];
        RANDOM.nextBytes(bytes);

        String suffix = HexFormat.of().formatHex(bytes);

        return normalized + "-" + suffix;
    }
}

