package com.example.movie_reservation.exception;

import lombok.Getter;

@Getter
public class NotFoundEntityException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotFoundEntityException() {
        super(ErrorCode.NOT_FOUND_ENTITY.getMessage());
        this.errorCode = ErrorCode.NOT_FOUND_ENTITY;
    }
}
