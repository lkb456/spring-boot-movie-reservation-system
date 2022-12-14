package com.example.springbootmoviereservationsystem.controller.dto.screening;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchScreenMovieDto {

    @NotBlank
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm", timezone = "Asia/Seoul")
    @NotBlank
    private LocalDateTime when;

}