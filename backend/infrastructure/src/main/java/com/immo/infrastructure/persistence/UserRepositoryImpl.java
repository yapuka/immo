package com.immo.infrastructure.persistence;

import com.immo.domain.user.User;
import com.immo.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public UserRepositoryImpl(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll();
    }

    @Override
    public User save(User user) {
        return springDataUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return springDataUserRepository.findById(id);
    }
}
