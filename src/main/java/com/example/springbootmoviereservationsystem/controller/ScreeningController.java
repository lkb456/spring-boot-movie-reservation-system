package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.screening.ScreeningResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.screening.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm") LocalDateTime startTime) {
        ScreeningSaveResponseDto screeningSaveResponseDto =
                ScreeningSaveResponseDto.of(screeningService.saveScreen(movieId, startTime));
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningSaveResponseDto);
    }

    @GetMapping("/screenings")
    public ResponseEntity<ScreeningResponseDto.PageScreenResponseDto> ScreensSearch(@RequestParam String title,
                                                                                    @RequestParam(value = "when", required = false)
                                                                                    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm") LocalDateTime whenScreened,
                                                                                    @PageableDefault Pageable pageable) {
        ScreeningResponseDto.PageScreenResponseDto pageScreenResponseDto = screeningService.searchScreens(title, whenScreened, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageScreenResponseDto);
    }
}
