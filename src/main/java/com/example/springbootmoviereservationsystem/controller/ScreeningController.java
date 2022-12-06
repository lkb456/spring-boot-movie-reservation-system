package com.example.springbootmoviereservationsystem.controller;

<<<<<<< HEAD
import com.example.springbootmoviereservationsystem.controller.dto.response.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ScreeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

=======
import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
>>>>>>> feature/test
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
<<<<<<< HEAD
=======

    @GetMapping("/screenings")
    public ResponseEntity<PageMovieResponseDto> screenFind(@RequestParam(value = "title", required = false) String title,
                                                           @RequestParam(value = "when", required = false) LocalDateTime whenScreened,
                                                           @PageableDefault Pageable pageable) {
        PageMovieResponseDto pageMovieResponseDto = screeningService.searchScreening(title, whenScreened, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageMovieResponseDto);
    }
>>>>>>> feature/test
}
