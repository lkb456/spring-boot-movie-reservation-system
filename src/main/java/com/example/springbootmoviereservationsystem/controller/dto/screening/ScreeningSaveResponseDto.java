package com.example.springbootmoviereservationsystem.controller.dto.screening;

import com.example.springbootmoviereservationsystem.domain.Screening;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto.MovieSaveDto;

@Getter
@Builder
@RequiredArgsConstructor
public class ScreeningSaveResponseDto {

    @JsonProperty("screening_id")
    private final Long id; // 상영 순번

    @JsonProperty("movie")
    private final MovieSaveDto movieSaveResponseDto; // 영화 정보

    @JsonProperty("when")
    private final LocalDateTime whenScreened; // 상영 시간

    public static ScreeningSaveResponseDto of(Screening savedScreening) {
        return ScreeningSaveResponseDto.builder()
                .id(savedScreening.getId())
                .movieSaveResponseDto(MovieSaveDto.of(savedScreening.getMovie()))
                .whenScreened(savedScreening.getWhenScreened())
                .build();
    }
}
