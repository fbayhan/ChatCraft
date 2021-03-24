package com.onurfatih.chat.chatcraft.core.api;

import com.onurfatih.chat.chatcraft.core.security.dto.JwtRequestDTO;
import com.onurfatih.chat.chatcraft.core.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth/")
public class AuthAPI {

    @Autowired
    private AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequestDTO loginRequest) {
        return ResponseEntity.ok(authService.isLogin(loginRequest.getUsername(), loginRequest.getPassword()));
    }


}
