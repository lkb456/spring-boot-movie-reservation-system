package com.example.movie_reservation.controller.seat.dto;

import com.example.movie_reservation.domain.seat.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SeatResponseDto {

    private final Integer seatNumber;

    public static SeatResponseDto of(Seat seat) {
        return SeatResponseDto.builder()
                .seatNumber(seat.getSeatNumber())
                .build();
    }
}