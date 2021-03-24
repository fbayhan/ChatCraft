package com.onurfatih.chat.chatcraft.core.security.service;

import com.onurfatih.chat.chatcraft.core.entity.User;
import com.onurfatih.chat.chatcraft.core.repository.UserRepository;
import com.onurfatih.chat.chatcraft.core.security.JwtUtils;
import com.onurfatih.chat.chatcraft.core.security.dto.JwtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public User getUsernamePassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public JwtResponseDTO isLogin(String username, String password) {
        User user = getUsernamePassword(username, password);
        if (user == null) return new JwtResponseDTO(null, null, null, null);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, user.getRoles(), authorities(user.getRoles()));
        ((UsernamePasswordAuthenticationToken) authentication).setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new JwtResponseDTO(jwt,
                user.getId(),
                user.getUsername(),
                user.getRoles());
    }

    public List<GrantedAuthority> authorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
        return authorities;
    }

}
