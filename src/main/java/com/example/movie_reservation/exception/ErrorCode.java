package com.example.movie_reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_ENTITY(HttpStatus.NOT_FOUND, "Not Found Entity!!");

    private final HttpStatus httpStatus;
    private final String message;

}