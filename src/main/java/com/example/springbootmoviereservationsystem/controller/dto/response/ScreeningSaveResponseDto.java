package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ScreeningSaveResponseDto {

    @JsonProperty("screening_id")
    private final Long id; // 상영 순번

    @JsonProperty("movie")
    private final MovieSaveResponseDto movieSaveResponseDto; // 영화 정보

    @JsonProperty("when")
    private final LocalDateTime whenScreened; // 상영 시간

    public static ScreeningSaveResponseDto of(Screening savedScreening) {
        return ScreeningSaveResponseDto.builder()
                .id(savedScreening.getId())
                .movieSaveResponseDto(MovieSaveResponseDto.of(savedScreening.getMovie()))
                .whenScreened(savedScreening.getWhenScreened())
                .build();
    }
}
