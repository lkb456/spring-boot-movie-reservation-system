package com.example.movie_reservation.domain.movie;

import com.example.movie_reservation.util.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.movie_reservation.fixture.CreateEntity.createMovie;
import static org.assertj.core.api.Assertions.assertThat;

class MovieTest {

    @Test
    @DisplayName("정보 업데이트")
    void updateInfo() {
        // given
        String expectedTitle = "싸움의 기술2";
        Movie movie = createMovie();

        // when
        movie.updateInfo(expectedTitle, Money.ZERO, movie.getRunningTime(), movie.getReleaseStatus());

        // then
        assertThat(expectedTitle).isEqualTo(movie.getTitle());
    }
}