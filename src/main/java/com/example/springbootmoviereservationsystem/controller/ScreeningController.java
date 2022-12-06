package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.SearchMovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.Seat;
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
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm") LocalDateTime startTime,
                                                               @RequestParam("seat") Seat seat,
                                                               @RequestParam("number") int seatNumber) {

        ScreeningSaveResponseDto screeningSaveResponseDto =
                ScreeningSaveResponseDto.of(screeningService.saveScreen(movieId, startTime, seat, seatNumber));

        System.out.println(screeningSaveResponseDto.getLine());
        System.out.println(screeningSaveResponseDto.getLineNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningSaveResponseDto);
    }

    @GetMapping("/screenings")
    public ResponseEntity<PageMovieResponseDto> screenFind(@RequestBody(required = false) SearchMovieRequestDto searchMovieRequestDto,
                                                           @PageableDefault Pageable pageable) {
        PageMovieResponseDto pageMovieResponseDto = screeningService.searchScreening(searchMovieRequestDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageMovieResponseDto);
    }
}
