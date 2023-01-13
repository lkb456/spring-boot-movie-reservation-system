package com.example.movie_reservation.controller.seat.dto;

import com.example.movie_reservation.domain.seat.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequestDto {

    @NotNull
    @JsonProperty("seat_number")
    private Integer seatNumber; // 좌석 번호

    public Seat toEntity() {
        return Seat.builder()
                .seatNumber(this.seatNumber)
                .build();
    }
}
