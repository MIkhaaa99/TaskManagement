package com.sarks.testtask.dto;

import com.sarks.testtask.entity.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreationDto {

    private String title;

    private String description;

    private Priority priority;

    private Long adminId;

    private Long performerId;

}
