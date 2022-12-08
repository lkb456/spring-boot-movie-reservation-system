package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.ReservationStatus;
import com.example.springbootmoviereservationsystem.domain.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SeatDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequestDto {

        @NotBlank
        @Pattern(regexp = "[a-hA-H]]", message = "a 부터 h 까지의 좌석만 가능합니다.")
        @JsonProperty("row_seat")
        private String rowSeat; // 행 좌석

        @NotNull
        @Max(value = 20, message = "좌석 수는 20번 까지 있습니다.")
        @JsonProperty("colum_number")
        private Integer columNumber; // 열 번호

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
