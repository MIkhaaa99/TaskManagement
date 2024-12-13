package com.sarks.testtask.dto.comment;

import lombok.Builder;

@Builder
public record CommentCreationDto(String text, Long employeeId, Long taskId) {
}
