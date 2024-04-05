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
public class Time {
    private int timeId;
    private String timePeriod;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
