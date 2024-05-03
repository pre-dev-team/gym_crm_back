package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchUserRespDto;
import lombok.Data;

@Data
public class AdminSearchUser {
    private int userId;
    private int userAccountId;
    private String name;
    private String username;
    private int reservationCount;
    private String phone;
    private String reservationId;

    public AdminSearchUserRespDto toAdminSearchUserRespDto() {
        return AdminSearchUserRespDto.builder()
                .userId(userId)
                .name(name)
                .reservationCount(reservationCount)
                .build();
    }
}
