package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.request.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ReservationSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/screenings/{id}/reservation")
    public ResponseEntity<ReservationSaveResponseDto> reserve(@PathVariable("id") Long screeningId,
                                  @RequestBody @Valid ReservationSaveRequestDto reservationSaveRequestDto) {
        ReservationSaveResponseDto reservationSaveResponseDto = reservationService.reserveSave(screeningId, reservationSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaveResponseDto);
    }

    @PutMapping("/reservation/{id}/ticket")
    public ResponseEntity<String> createTicket(@PathVariable("id") Long reservationId) {
        reservationService.ticketPublish(reservationId);
        return ResponseEntity.status(HttpStatus.OK).body("ticket");
    }
}
