package com.example.movie_reservation.module.screening.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(value = "영화 상영정보", description = "영화 식별자, 상영 시간")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningSaveRequestDto {

    @NotNull
    @JsonProperty("movie_id")
    @ApiModelProperty(value = "영화 식별자", example = "1")
    private Long movieId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm")
    @ApiModelProperty(value = "상영 시간", example = "2023-02-20-08:30")
    private LocalDateTime when;
}
