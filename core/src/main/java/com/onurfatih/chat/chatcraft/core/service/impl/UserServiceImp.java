package com.onurfatih.chat.chatcraft.core.service.impl;

import com.onurfatih.chat.chatcraft.core.entity.User;
import com.onurfatih.chat.chatcraft.core.repository.UserRepository;
import com.onurfatih.chat.chatcraft.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void insert(User user) {
        userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

    public List<User> searchUser(String name) {
        return userRepository.findByNameRegex(".*" + name + ".*");
    }

    public User getUserId(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> search(String name) {
        return userRepository.findByNameRegex(".*" + name + ".*");
    }

    @Override
    public User getMyProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return getUserId(user.getId());
    }

    @Override
    public User getUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
