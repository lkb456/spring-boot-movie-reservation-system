package com.example.springbootmoviereservationsystem.controller.seat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
