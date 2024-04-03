package com.predev.gymcrm.jwt;

import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.repository.UserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    public String generateJwt(User user) {

        int userId = user.getUserId();
        String username = user.getUserUsername();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        List<Reservation> reservations = user.getReservations();
        // 6시간
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 6));
        String accessToken = "";

        if(user.getAuthorities() == null) {
            accessToken = Jwts.builder()
                    .claim("userId",userId)
                    .claim("username",username)
                    .claim("authorities",authorities)
                    .setExpiration(expireDate)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } else {
            accessToken = Jwts.builder()
                    .claim("userId", userId)
                    .claim("username", username)
                    .claim("authorities", authorities)
                    .claim("reservations", reservations)
                    .setExpiration(expireDate)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }

        return accessToken;
    }

}
