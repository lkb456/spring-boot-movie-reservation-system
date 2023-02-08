package com.example.movie_reservation.module.seat.dto;

import com.example.movie_reservation.module.seat.domain.Seat;
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