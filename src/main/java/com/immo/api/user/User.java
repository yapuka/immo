package com.immo.api.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("users")
public record User(
        @Id String id,
        String email,
        String displayName,
        Instant createdAt,
        Instant updatedAt) {
}
