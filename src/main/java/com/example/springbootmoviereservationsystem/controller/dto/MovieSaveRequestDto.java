package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSaveRequestDto {

    private String title; // 영화 제목
    private Long fee; // 영화 요금

    @JsonProperty("time")
    private Duration runningTime;

    @JsonProperty("status")
    private ReleaseStatus releaseStatus;

    public Movie toEntity() {
        return Movie.builder()
                .title(this.title)
                .fee(this.fee)
                .runningTime(this.runningTime)
                .releaseStatus(this.releaseStatus)
                .build();
    }

}
