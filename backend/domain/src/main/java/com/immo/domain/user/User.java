package com.immo.domain.user;

import java.time.Instant;

public record User(
        String id,
        String email,
        String displayName,
        Instant createdAt,
        Instant updatedAt) {
}
