package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTest {

    @Test
    @DisplayName("정보 업데이트")
    void updateInfo() {
        // given
        String expectedTitle = "싸움의 기술2";
        Movie movie = createMovie();

        // when
        movie.updateInfo(expectedTitle, movie.getFee(), movie.getRunningTime(), movie.getReleaseStatus());

        // then
        assertThat(expectedTitle).isEqualTo(movie.getTitle());
    }

    private Movie createMovie() {
        return Movie.builder()
                .title("싸움의 기술")
                .fee(10000L)
                .runningTime(Duration.ofMinutes(20000))
                .releaseStatus(ReleaseStatus.RELEASE)
                .build();
    }
}