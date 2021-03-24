package com.onurfatih.chat.chatcraft.core;

import com.onurfatih.chat.chatcraft.core.entity.User;
import com.onurfatih.chat.chatcraft.core.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.onurfatih")
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    @Autowired
    private UserServiceImp userService;

//    @Autowired
//    PasswordEncoder encoder;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        if (userService.count() == 0) {
            User user1 = new User();
            user1.setName("Onur");
            user1.setUsername("onur");
            user1.setRoles(roles);
            user1.setPassword("123");
            userService.insert(user1);

            User user2 = new User();
            user2.setName("Fatih");
            user2.setUsername("fatih");
            user2.setRoles(roles);
            user2.setPassword("123");
            userService.insert(user2);

            User user3 = new User();
            user3.setName("Ali");
            userService.insert(user3);

            User user4 = new User();
            user4.setName("Veli");
            userService.insert(user4);

            User user5 = new User();
            user5.setName("Onurcan");
            userService.insert(user5);

            User user6 = new User();
            user6.setName("fatih can");
            userService.insert(user6);

//            user1.getFriendRequest().add(user2);
//            userService.insert(user1);

        }

    }

}
