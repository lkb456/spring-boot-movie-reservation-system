package com.example.springbootmoviereservationsystem.controller.reservation.dto;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerResponseDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.controller.seat.dto.SeatResponseDto;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@RequiredArgsConstructor
public class ReservationResponseDto {

    @JsonProperty("consumer")
    private final ConsumerResponseDto consumerResponseDto;

    @JsonProperty("audience_count")
    private final int audienceCount;

    @JsonProperty("screening")
    private final ScreeningSaveResponseDto screeningSaveResponseDto;

    @JsonProperty("total_fee")
    private final Long fee;

    private final List<SeatResponseDto> seats;

    public static ReservationResponseDto of(Reservation savedReservation) {
        return ReservationResponseDto.builder()
                .consumerResponseDto(ConsumerResponseDto.of(savedReservation.getConsumer()))
                .audienceCount(savedReservation.getAudienceCount())
                .screeningSaveResponseDto(ScreeningSaveResponseDto.of(savedReservation.getScreening()))
                .fee(savedReservation.getFee().longValue())
                .seats(savedReservation.getSeats().stream()
                        .map(SeatResponseDto::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
