package com.onurfatih.chat.chatcraft.core.service;

import com.onurfatih.chat.chatcraft.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> search(String name);

    public User getMyProfile();

    public User getUsername(String username);

}
