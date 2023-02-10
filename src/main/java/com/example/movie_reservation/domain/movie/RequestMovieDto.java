package com.example.movie_reservation.domain.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RequestMovieDto {

    @NotBlank
    private String title;

    @JsonProperty("time")
    @NotNull
    private Duration runningTime;

    @JsonProperty("release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate releaseDate;

    @Builder
    public RequestMovieDto(String title, Duration runningTime, LocalDate releaseDate) {
        this.title = title;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
    }

    public Movie toEntity() {
        return Movie.builder()
                .title(this.title)
                .runningTime(this.runningTime)
                .releaseDate(this.releaseDate)
                .build();
    }
}
