package com.example.movie_reservation.domain.actor;

import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.movie.ReleaseStatus;
import com.example.movie_reservation.util.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ActorTest {

    @Test
    @DisplayName("연관관계 매핑 메서드 테스트")
    void ActorTest() {
        // given
        Movie movie = Movie.builder()
                .title("영화")
                .amount(Money.ZERO)
                .runningTime(Duration.ZERO)
                .releaseStatus(ReleaseStatus.RELEASE)
                .build();

        Actor actor = Actor.builder()
                .firstName("서")
                .lastName("인국")
                .birthDate(LocalDate.MIN)
                .image("사진.png")
                .content("내용")
                .build();

        // when
        movie.addActor(Set.of(actor));

        // then
        assertThat(movie.getActors().contains(actor)).isTrue();
    }
}