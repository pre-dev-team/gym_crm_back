package com.predev.gymcrm.jwt;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.repository.AuthMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;
    private final AuthMapper authMapper;
    private final Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 6));

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Autowired AuthMapper authMapper
           ) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.authMapper = authMapper;
    }

    public String generateJwt(Account account) {
            int accountId = account.getAccountId();
            String username = account.getUsername();
            Collection<? extends GrantedAuthority> authorities = account.getAuthorities();
            String accessToken = Jwts.builder()
                    .claim("accountId",accountId)
                    .claim("username",username)
                    .claim("authorities",authorities)
                    .setExpiration(expireDate)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        return accessToken;
    }

    public Claims getClaims(String encodedToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(encodedToken)
                .getBody();
    }

    public Authentication getAuthentication(Claims claims) {
        String username = claims.get("username").toString();
        Account account = authMapper.findAccountByUsername(username);

        if (account == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(account.toPrincipal(),null, account.toPrincipal().getAuthorities());
    }

}
