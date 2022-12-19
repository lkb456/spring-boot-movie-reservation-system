package com.example.springbootmoviereservationsystem.controller.screening;

import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningResponseDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveResponseDto;
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

    @PostMapping("/screenings")
    public ResponseEntity<ScreeningSaveResponseDto> screenSave(@RequestBody ScreeningSaveRequestDto screeningSaveRequestDto) {
        ScreeningSaveResponseDto screeningSaveResponseDto =
                ScreeningSaveResponseDto.of(screeningService.saveScreen(screeningSaveRequestDto));
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
