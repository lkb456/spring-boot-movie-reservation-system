package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.domain.Screening;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
@RequiredArgsConstructor
public class ScreeningSaveResponseDto {

    @JsonProperty("screening_id")
    private final Long id; // 상영 순번

    @JsonProperty("movie")
    private final MovieSaveResponseDto movieSaveResponseDto; // 영화 정보

    private final String line; // 좌석 열

    private final List<Integer> lineNumber; // 좌석 번호

    @JsonProperty("when")
    private final LocalDateTime whenScreened; // 상영 시간

    public static ScreeningSaveResponseDto of(Screening savedScreening) {
        return ScreeningSaveResponseDto.builder()
                .id(savedScreening.getId())
                .movieSaveResponseDto(MovieSaveResponseDto.of(savedScreening.getMovie()))
                .line(savedScreening.getSeat().getLine())
                .lineNumber(savedScreening.getSeat().getSeatLine()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(Boolean.TRUE))
                                .map(Map.Entry::getKey).collect(Collectors.toList()))
                .whenScreened(savedScreening.getWhenScreened())
                .build();
    }
}
