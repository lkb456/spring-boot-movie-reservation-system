package com.example.movie_reservation.controller.seat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequestDto {

    @NotNull
    @JsonProperty("seat_id")
    private Long seatId; // 좌석 번호

}
