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
public class User {
    private int userId;
    private String userUsername;
    private String userPassword;
    private String userName;
    private String userPhone;
    private String userEmail;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
