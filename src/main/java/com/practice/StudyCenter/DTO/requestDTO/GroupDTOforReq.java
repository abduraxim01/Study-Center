package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDTOforReq {

    private String name;

    private LocalTime time;

    private Set<DayOfWeek> days;
}
