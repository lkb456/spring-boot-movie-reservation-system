package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Getter
@RequiredArgsConstructor
public class MovieResponseDto {

    private final String title;

    private final Long fee;

    @JsonProperty("running_time")
    private final Duration runningTime;

    private final ReleaseStatus status;

}
