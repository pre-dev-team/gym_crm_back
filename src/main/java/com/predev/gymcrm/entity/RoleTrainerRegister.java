package com.predev.gymcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleTrainerRegister {
    private int roleRegisterId;
    private int roleId;
    private int trainerId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Role role;
}
