package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchHolidayRespDto;
import com.predev.gymcrm.dto.resp.SelectHolidayRespDto;
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

    private Trainer trainer;
    private TrainerAccountView trainerAccountView;

    public SelectHolidayRespDto toSelectHolidayRespDto(String name) {
        return SelectHolidayRespDto.builder()
                .holidayId(holidayId)
                .holidayDate(holidayDate)
                .timeId(timeId)
                .name(name)
                .confirm(confirm)
                .build();
    }
}
