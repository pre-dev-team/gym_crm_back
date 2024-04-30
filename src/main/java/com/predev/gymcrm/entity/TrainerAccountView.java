package com.predev.gymcrm.entity;

import lombok.Data;

@Data
public class TrainerAccountView {
    private int accountId;
    private int trainerId;
    private String trainerProfileImgUrl;
    private int roleId;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
}
