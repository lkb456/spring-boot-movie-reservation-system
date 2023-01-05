package com.example.springbootmoviereservationsystem.controller.screening;

import com.example.springbootmoviereservationsystem.controller.screening.dto.PageScreenResponseDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Validated
@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @PostMapping("/screenings")
    public ResponseEntity<ScreeningSaveResponseDto> screenSave(@Valid @RequestBody final ScreeningSaveRequestDto screeningSaveRequestDto) {
        ScreeningSaveResponseDto screeningSaveResponseDto =
                ScreeningSaveResponseDto.of(screeningService.saveScreen(screeningSaveRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningSaveResponseDto);
    }

    @GetMapping("/screenings")
    public ResponseEntity<PageScreenResponseDto> screensSearch(@RequestParam(value = "title") final String title,
                                                               @RequestParam(value = "when", required = false)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm") final LocalDateTime whenScreened,
                                                               @PageableDefault Pageable pageable) {
        PageScreenResponseDto pageScreenResponseDto = screeningService.searchScreens(title, whenScreened, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageScreenResponseDto);
    }
}
