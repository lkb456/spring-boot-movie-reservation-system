package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.controller.dto.SeatDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.consumer.ConsumerSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@RequiredArgsConstructor
public class ReservationSaveResponseDto {

    @JsonProperty("consumer")
    private final ConsumerSaveResponseDto consumerSaveResponseDto;

    @JsonProperty("audience_count")
    private final int audienceCount;

    @JsonProperty("screening")
    private final ScreeningSaveResponseDto screeningSaveResponseDto;

    @JsonProperty("total_fee")
    private final Long fee;

    private final List<SeatDto.ResponseDto> seats;

    public static ReservationSaveResponseDto of(Reservation savedReservation) {
        return ReservationSaveResponseDto.builder()
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
