package com.example.movie_reservation.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class ResponseErrorDto {
    private final int statusCode;
    private final HttpStatus httpStatus;
    private final String message;
    private final List<FieldError> fieldErrors;
    private final LocalDateTime timestamp;

    @Builder
    public ResponseErrorDto(HttpStatus httpStatus, String message, List<FieldError> fieldErrors) {
        this.statusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.timestamp = LocalDateTime.now();
    }
}
