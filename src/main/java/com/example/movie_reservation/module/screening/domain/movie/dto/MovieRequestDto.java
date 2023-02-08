package com.example.movie_reservation.module.screening.domain.movie.dto;

import com.example.movie_reservation.module.screening.domain.movie.domain.ReleaseStatus;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.infra.util.Money;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@ApiModel(value = "영화 정보", description = "제목, 요금, 상영 시간, 개봉 상태")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    @NotBlank(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    @ApiModelProperty(value = "영화 제목", example = "범죄도시")
    private String title;

    @NotNull
    @ApiModelProperty(value = "요금", example = "14000")
    private Long fee;

    @NotNull
    @JsonProperty("time")
    @ApiModelProperty(value = "상영 시간", example = "18000")
    private Duration runningTime;

    @JsonProperty("status")
    @NotNull(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    @ApiModelProperty(value = "개봉 상태", example = "RELEASE")
    private ReleaseStatus releaseStatus;

    public Movie toEntity() {
        return Movie.builder()
                .title(this.title)
                .amount(Money.wons(this.fee))
                .runningTime(this.runningTime)
                .releaseStatus(this.releaseStatus)
                .build();
    }
}
