package com.example.springbootmoviereservationsystem.controller.movie.dto;

import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MovieResponseDto {

    @Getter
    public static class PageMovieDto {

        private final List<MovieDtoProjection> elements;
        private final int elementsSize;
        private final int currentPage;
        private final int totalPage;
        private final int pageSize;

        @Builder
        private PageMovieDto(Page<MovieDtoProjection> movieResponseDtoPage) {
            this.elements = movieResponseDtoPage.getContent();
            this.elementsSize = elements.size();
            this.currentPage = movieResponseDtoPage.getNumber();
            this.totalPage = movieResponseDtoPage.getTotalPages();
            this.pageSize = movieResponseDtoPage.getSize();
        }

        public static PageMovieDto of(Page<MovieDtoProjection> movieResponseDtoPage) {
            return PageMovieDto.builder()
                    .movieResponseDtoPage(movieResponseDtoPage)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class MovieSaveDto {

        @JsonProperty("movie_id")
        private final Long id; // 저장 순번

        private final String title; // 제목

        private final Long fee; // 요금

        @JsonProperty("running_time")
        private final Duration runningTime; // 상영 시간

        @JsonProperty("status")
        private final ReleaseStatus releaseStatus; // 개봉 상태

        @JsonProperty("create_at")
        private final LocalDateTime createAt; // 정보 저장 시간

        public static MovieSaveDto of(Movie movie) {
            return MovieSaveDto.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .fee(movie.getFee().getAmount().longValue())
                    .runningTime(movie.getRunningTime())
                    .releaseStatus(movie.getReleaseStatus())
                    .createAt(movie.getCreateAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class MovieDto {

        private final String title;

        private final Long fee;

        @JsonProperty("running_time")
        private final Duration runningTime;

        private final ReleaseStatus status;

        public static MovieDto of(Movie movie) {
            return MovieDto.builder()
                    .title(movie.getTitle())
                    .fee(movie.getFee().getAmount().longValue())
                    .runningTime(movie.getRunningTime())
                    .status(movie.getReleaseStatus())
                    .build();
        }
    }
}
