package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.response.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Valid
@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @PostMapping("/movies/{id}/screenings")
    public ResponseEntity<ScreeningSaveResponseDto> screenSave(@PathVariable(name = "id") Long movieId,
                                                               @RequestParam("when")
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm") LocalDateTime whenScreened) {
        ScreeningSaveResponseDto screeningSaveResponseDto =
                ScreeningSaveResponseDto.of(screeningService.saveScreen(movieId, whenScreened));
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningSaveResponseDto);
    }
}
