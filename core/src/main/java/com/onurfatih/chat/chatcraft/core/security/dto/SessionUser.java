package com.onurfatih.chat.chatcraft.core.security.dto;

import com.onurfatih.chat.chatcraft.core.entity.User;
import lombok.Data;

@Data
public class SessionUser {

    private String username;

    private String password;

    public SessionUser(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public SessionUser() {
    }

}
