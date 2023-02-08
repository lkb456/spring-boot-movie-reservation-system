package com.example.movie_reservation.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ExceptionResponseDto {

    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final int statusCode;
    private final HttpStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldErrors = new ArrayList<>();
    private final String message;

    @Builder
    public ExceptionResponseDto(HttpStatus status, List<FieldError> fieldErrors ,String message) {
        this.statusCode = status.value();
        this.status = status;
        this.fieldErrors = fieldErrors;
        this.message = message;
    }
}
