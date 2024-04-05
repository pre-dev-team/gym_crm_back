package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.req.SearchUserReqDto;
import com.predev.gymcrm.security.Principal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    private int accountId;
    private int roleId;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private User user;
    private Trainer trainer;
    private Role role;
    private List<Reservation> reservations;

    public List<SimpleGrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    public Principal toPrincipal() {
        return Principal.builder()
                .userId(accountId)
                .username(username)
                .email(email)
                .authorities(this.getAuthorities())
                .build();
    }

    public SearchUserReqDto toSearchUserReqDto() {
        return SearchUserReqDto.builder()
                .accountId(accountId)
                .build();
    }

}
