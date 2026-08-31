package com.immo.exposition.user;

import com.immo.domain.user.User;

import java.time.Instant;

public record UserResponse(
        String id,
        String email,
        String displayName,
        Instant createdAt,
        Instant updatedAt) {

    static UserResponse from(User user) {
        return new UserResponse(
                user.id(),
                user.email(),
                user.displayName(),
                user.createdAt(),
                user.updatedAt());
    }
}
