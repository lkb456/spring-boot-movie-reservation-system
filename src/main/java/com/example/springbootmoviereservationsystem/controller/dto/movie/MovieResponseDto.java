package com.example.springbootmoviereservationsystem.controller.dto.movie;

import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MovieResponseDto {

    @Getter
    public static class PageMovieDto {

        private final List<MovieDto> elements;
        private final int elementsSize;
        private final int currentPage;
        private final int totalPage;
        private final int pageSize;

        @Builder
        private PageMovieDto(Page<MovieDto> movieResponseDtoPage) {
            this.elements = movieResponseDtoPage.getContent();
            this.elementsSize = elements.size();
            this.currentPage = movieResponseDtoPage.getNumber();
            this.totalPage = movieResponseDtoPage.getTotalPages();
            this.pageSize = movieResponseDtoPage.getSize();
        }

        public static PageMovieDto of(Page<MovieDto> movieResponseDtoPage) {
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
                    .fee(movie.getFee())
                    .runningTime(movie.getRunningTime())
                    .releaseStatus(movie.getReleaseStatus())
                    .createAt(movie.getCreateAt())
                    .build();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class MovieDto {

        private final String title;

        private final Long fee;

        @JsonProperty("running_time")
        private final Duration runningTime;

        private final ReleaseStatus status;

    }
}
