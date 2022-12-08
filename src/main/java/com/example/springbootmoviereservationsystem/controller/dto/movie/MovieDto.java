package com.example.springbootmoviereservationsystem.controller.dto.movie;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Getter
@RequiredArgsConstructor
public class MovieDto {

    private final String title;

    private final Long fee;

    @JsonProperty("running_time")
    private final Duration runningTime;

    private final ReleaseStatus status;

}
