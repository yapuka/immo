package com.immo.service.user;

import com.immo.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(String email, String displayName);
}
