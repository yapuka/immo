package com.immo.infrastructure.persistence;

import com.immo.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataUserRepository extends MongoRepository<User, String> {
}
