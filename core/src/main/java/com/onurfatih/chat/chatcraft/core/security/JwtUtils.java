package com.onurfatih.chat.chatcraft.core.security;

import com.onurfatih.chat.chatcraft.core.entity.User;
import com.onurfatih.chat.chatcraft.core.security.service.RedisService;
import com.onurfatih.chat.chatcraft.core.security.dto.SessionUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private RedisService redisService;

    //	@Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret = "ChatSecretKey";

    //	@Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationSecond = 10; // 6000 sn;

    public String generateJwtToken(Authentication authentication) {

        User user = (User) authentication.getDetails();

        String token = Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + (jwtExpirationSecond * 1000)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        redisService.setValue(token, new SessionUser(user), TimeUnit.SECONDS, jwtExpirationSecond);

        return token;
    }

    public String getUserNameFromJwtToken(String token) {
        SessionUser user = (SessionUser) redisService.getValue(token, SessionUser.class);
        if (user == null) new SignatureException("");
        redisService.setValue(token, user, TimeUnit.SECONDS, jwtExpirationSecond);
        return user.getUsername();
    }

    public boolean validateJwtToken(String authToken) {
        SessionUser user = (SessionUser) redisService.getValue(authToken, SessionUser.class);
        if (user == null) return false;
        return true;
    }
}
