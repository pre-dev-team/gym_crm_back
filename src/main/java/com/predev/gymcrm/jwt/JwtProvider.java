package com.predev.gymcrm.jwt;

import com.predev.gymcrm.repository.UserMapper;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtProvider {

    private final Key key;
    private final UserMapper userMapper;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Autowired UserMapper userMapper) {

        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

}
