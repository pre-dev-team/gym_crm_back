package com.predev.gymcrm.entity;

import com.predev.gymcrm.security.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int userId;
    private String userUsername;
    private String userPassword;
    private String userName;
    private String userPhone;
    private String userEmail;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<RoleRegister> roleRegisters;
    private List<Reservation> reservations;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return roleRegisters.stream()
                .map(register -> new SimpleGrantedAuthority(register.getRole().getRoleName()))
                .collect(Collectors.toList());
    }

    public PrincipalUser toPrincipal() {
        return PrincipalUser.builder()
                .userId(userId)
                .username(userUsername)
                .authorities(this.getAuthorities())
                .build();
    }

}
