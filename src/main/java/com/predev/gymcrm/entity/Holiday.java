package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchHolidayRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Holiday {
    private int holidayId;
    private int trainerId;
    private String holidayDate;
    private int timeId;
    private int confirm;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<Time> times;
    private Trainer trainer;

    public AdminSearchHolidayRespDto toAdminSearchHolidayRespDto() {
        return AdminSearchHolidayRespDto.builder()
                .holidayId(holidayId)
                .trainerId(trainerId)
                .timeId(timeId)
                .confirm(confirm)
                .createDate(createDate)
                .holidayDate(holidayDate)
                .trainerName(trainer.getAccount().getName())
                .build();
    }
}
