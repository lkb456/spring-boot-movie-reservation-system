package com.example.movie_reservation.controller.seat.dto;

import com.example.movie_reservation.domain.reservation.ReservationStatus;
import com.example.movie_reservation.domain.seat.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SeatResponseDto {

    private final String row;
    private final Integer colum;
    private final ReservationStatus reservationStatus;

    public static SeatResponseDto of(Seat seat) {
        return SeatResponseDto.builder()
                .row(seat.getRowNumber())
                .colum(seat.getColumNumber())
                .reservationStatus(seat.getReservationStatus())
                .build();
    }
}