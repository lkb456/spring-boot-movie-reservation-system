package com.example.movie_reservation.controller.reservation;

import com.example.movie_reservation.controller.reservation.dto.PopularMovieResponseDto;
import com.example.movie_reservation.controller.reservation.dto.ReservationResponseDto;
import com.example.movie_reservation.controller.reservation.dto.ReservationSaveRequestDto;
import com.example.movie_reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<ReservationResponseDto> reserve(@Valid @RequestBody ReservationSaveRequestDto reservationSaveRequestDto) {
        ReservationResponseDto reservationSaveResponseDto = reservationService.reserveSave(reservationSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaveResponseDto);
    }

    @PostMapping("/reservation/{id}/ticket")
    public ResponseEntity<Void> createTicket(@PathVariable("id") Long reservationId) {
        reservationService.ticketPublish(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/reservation/{id}/cancel")
    public ResponseEntity<Void> cancelReserve(@PathVariable("id") Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponseDto> findReservation(@PathVariable("id") Long reservationId) {
        ReservationResponseDto reservationResponseDto = ReservationResponseDto.of(reservationService.findReservation(reservationId));
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponseDto);
    }

    @GetMapping("/reservation/popular")
    public ResponseEntity<List<PopularMovieResponseDto>> findPopularMovie() {
        List<PopularMovieResponseDto> popularMovieResponseDto = reservationService.bestMovieFind();
        return ResponseEntity.status(HttpStatus.OK).body(popularMovieResponseDto);
    }
}
