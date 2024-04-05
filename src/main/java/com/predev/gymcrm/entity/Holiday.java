package com.predev.gymcrm.entity;

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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<Time> times;
}
