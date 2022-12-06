package com.example.springbootmoviereservationsystem.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMovieRequestDto {

    @NotBlank
    private String title;

    @JsonProperty("start")
    @NotBlank
    private LocalDateTime startTime;
}
