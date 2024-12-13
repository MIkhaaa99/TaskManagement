package com.sarks.testtask.dto.task;

import com.sarks.testtask.entity.Priority;
import lombok.Builder;

@Builder
public record TaskCreationDto(String title, String description, Priority priority, Long adminId, Long performerId) {

}
