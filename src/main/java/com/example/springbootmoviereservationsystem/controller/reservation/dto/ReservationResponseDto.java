package com.example.springbootmoviereservationsystem.controller.reservation.dto;

import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.controller.seat.SeatDto;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerResponseDto.ConsumerSaveResponseDto;

@Builder
@Getter
@RequiredArgsConstructor
public class ReservationResponseDto {

    @JsonProperty("consumer")
    private final ConsumerSaveResponseDto consumerSaveResponseDto;

    @JsonProperty("audience_count")
    private final int audienceCount;

    @JsonProperty("screening")
    private final ScreeningSaveResponseDto screeningSaveResponseDto;

    @JsonProperty("total_fee")
    private final Long fee;

    private final List<SeatDto.ResponseDto> seats;

    public static ReservationResponseDto of(Reservation savedReservation) {
        return ReservationResponseDto.builder()
                .consumerSaveResponseDto(ConsumerSaveResponseDto.of(savedReservation.getConsumer()))
                .audienceCount(savedReservation.getAudienceCount())
                .screeningSaveResponseDto(ScreeningSaveResponseDto.of(savedReservation.getScreening()))
                .fee(savedReservation.getFee())
                .seats(savedReservation.getSeats()
                        .stream()
                        .map(SeatDto.ResponseDto::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
