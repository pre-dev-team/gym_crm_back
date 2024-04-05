package com.predev.gymcrm.entity;

import com.predev.gymcrm.security.Principal;
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
public class Trainer {
    private int trainerId;
    private String trainerUserName;
    private String trainerName;
    private String trainerPassword;
    private String trainerEmail;
    private String trainerPhone;
    private String trainerProfileImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<RoleTrainerRegister> roleTrainerRegisters;
    public List<SimpleGrantedAuthority> getAuthorities() {
        return roleTrainerRegisters.stream()
                .map(register -> new SimpleGrantedAuthority(register.getRole().getRoleName()))
                .collect(Collectors.toList());
    }
    public Principal toPrincipal() {
        return Principal.builder()
                .userId(trainerId)
                .username(trainerName)
                .authorities(this.getAuthorities())
                .build();
    }
}
