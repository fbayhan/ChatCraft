package com.onurfatih.chat.chatcraft.core.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponseDTO {

    private String token;
    private String id;
    private String username;
    private List<String> roles;

}
