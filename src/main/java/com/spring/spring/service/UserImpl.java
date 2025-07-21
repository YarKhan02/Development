package com.spring.spring.service;

import com.spring.spring.entity.User;
import com.spring.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            userRepository.save(new User(email));
        }
    }
}
