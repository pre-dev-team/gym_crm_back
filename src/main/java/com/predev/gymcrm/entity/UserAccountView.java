package com.predev.gymcrm.entity;

import lombok.Data;

@Data
public class UserAccountView {
    private int accountId;
    private int userId;
    private int roleId;
    private String username;
    private String name;
    private String password;
    private String phone;
    private String email;
}
