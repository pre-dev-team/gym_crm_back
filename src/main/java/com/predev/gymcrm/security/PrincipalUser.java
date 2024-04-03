package com.predev.gymcrm.security;

import com.predev.gymcrm.entity.Reservation;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@Builder
public class PrincipalUser implements UserDetails {
    private int userId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
