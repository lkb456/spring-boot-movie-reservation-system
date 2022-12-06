package com.example.springbootmoviereservationsystem.controller.dto.request;

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
public class MovieUpdateRequestDto {

    @NotBlank(message = "공백 없이 입력하세요.")
    private String title; // 제목

    @NotNull
    private Long fee; // 요금

    @JsonProperty("running_time")
    @NotNull
    private Duration runningTime; // 상영 시간

    @JsonProperty("status")
    @NotNull
    private ReleaseStatus releaseStatus; // 개봉 상태


}
