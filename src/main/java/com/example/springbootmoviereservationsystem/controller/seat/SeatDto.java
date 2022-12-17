package com.example.springbootmoviereservationsystem.controller.seat;

import com.example.springbootmoviereservationsystem.domain.seat.Seat;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

public class SeatDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequestDto {

        @NotNull
        @JsonProperty("seat_id")
        private Long seatId; // 좌석 번호

    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ResponseDto {

        private final String row;
        private final Integer columNumber;
        private final ReservationStatus reservationStatus;

        public static ResponseDto of(Seat seat) {
            return ResponseDto.builder()
                    .row(seat.getRowNumber())
                    .columNumber(seat.getColumNumber())
                    .reservationStatus(seat.getReservationStatus())
                    .build();
        }
    }
}
