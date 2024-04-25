package com.predev.gymcrm.entity;

import com.predev.gymcrm.dto.resp.AdminSearchHolidayRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminSearchHoliday {
    private int trainerId;
    private String holidayDate;
    private int startTimeId;
    private int endTimeId;
    private LocalDateTime crateDate;
    private String name;

    public AdminSearchHolidayRespDto toAdminSearchHolidayRespDto() {
        return AdminSearchHolidayRespDto.builder()
                .trainerId(trainerId)
                .holidayDate(holidayDate)
                .startTimeId(startTimeId)
                .endTimeId(endTimeId)
                .crateDate(crateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .name(name)
                .build();
    }
}
