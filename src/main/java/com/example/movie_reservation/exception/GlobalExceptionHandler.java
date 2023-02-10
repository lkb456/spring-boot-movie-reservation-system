package com.example.movie_reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.w3c.dom.ranges.RangeException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RangeException.class)
    public ResponseEntity<ResponseErrorDto> dateRangeException(RangeException e) {
        ResponseErrorDto responseErrorDto = ResponseErrorDto.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return ResponseEntity.ok(responseErrorDto);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        ResponseErrorDto responseErrorDto = ResponseErrorDto.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity.ok(responseErrorDto);
    }
}
