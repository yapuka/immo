package com.immo.service.user;

import com.immo.domain.user.User;
import com.immo.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(String email, String displayName) {
        Instant now = Instant.now();
        User user = new User(null, email, displayName, now, now);
        return userRepository.save(user);
    }
}
