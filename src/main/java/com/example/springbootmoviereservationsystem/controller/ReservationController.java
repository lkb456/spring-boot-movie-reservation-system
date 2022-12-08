package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.reservation.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.reservation.ReservationSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<ReservationSaveResponseDto> reserve(@RequestBody @Valid ReservationSaveRequestDto reservationSaveRequestDto) {
        ReservationSaveResponseDto reservationSaveResponseDto = reservationService.reserveSave(reservationSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaveResponseDto);
    }

    @PostMapping("/reservation/{id}/ticket")
    public ResponseEntity<Void> createTicket(@PathVariable("id") Long reservationId) {
        reservationService.ticketPublish(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
