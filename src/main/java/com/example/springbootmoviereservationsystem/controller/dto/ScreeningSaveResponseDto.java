package com.example.springbootmoviereservationsystem.controller.dto;

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
    private final Long id;

    @JsonProperty("movie")
    private final MovieSaveResponseDto movieSaveResponseDto;

    @JsonProperty("when")
    private final LocalDateTime whenScreened;

    public static ScreeningSaveResponseDto of(Long id, MovieSaveResponseDto movieSaveResponseDto, LocalDateTime whenScreened) {
        return ScreeningSaveResponseDto.builder()
                .id(id)
                .movieSaveResponseDto(movieSaveResponseDto)
                .whenScreened(whenScreened)
                .build();
    }
}
