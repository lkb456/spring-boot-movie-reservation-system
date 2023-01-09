package com.example.springbootmoviereservationsystem.controller.movie.dto;

import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    @NotBlank(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    private String title; // 제목

    @NotNull
    private Long fee; // 요금

    @NotNull
    @JsonProperty("time")
    private Duration runningTime; // 상영 시간

    @JsonProperty("status")
    @NotNull(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    private ReleaseStatus releaseStatus; // 개봉 상태

    public Movie toEntity() {
        return Movie.builder()
                .title(this.title)
                .amount(Money.wons(this.fee))
                .runningTime(this.runningTime)
                .releaseStatus(this.releaseStatus)
                .build();
    }
}
