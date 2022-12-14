package com.example.springbootmoviereservationsystem.controller.dto.movie;

import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

public class MovieRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieSaveDto {

        @NotBlank(message = "공백 없이 입력하세요.")
        private String title; // 영화 제목

        @NotBlank(message = "공백 없이 입력하세요.")
        private Long fee; // 영화 요금

        @JsonProperty("time")
        @NotNull
        private Duration runningTime;

        @JsonProperty("status")
        @NotNull
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieUpdateDto {

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
}
