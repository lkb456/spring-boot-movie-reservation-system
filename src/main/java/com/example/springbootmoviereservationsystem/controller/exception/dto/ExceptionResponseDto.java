package com.example.springbootmoviereservationsystem.controller.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExceptionResponseDto {

    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final int statusCode;
    private final HttpStatus status;
    private final String message;

    @Builder
    public ExceptionResponseDto(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.status = status;
        this.message = message;
    }
}
