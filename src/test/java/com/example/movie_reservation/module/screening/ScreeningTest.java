package com.example.movie_reservation.module.screening;

import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.reservation.domain.Reservation;
import com.example.movie_reservation.module.reservation.domain.ReservationStatus;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.infra.util.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.movie_reservation.fixture.CreateEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ScreeningTest {

    @Test
    @DisplayName("예매 정보 생성 하기")
    void reserve() {
        // given
        Consumer consumer = createConsumer();
        Movie movie = createMovie();
        Screening screening = createScreening(movie);

        // when
        int audienceCount = 5;
        Reservation reservation = screening.reserve(consumer, audienceCount, Money.ZERO);

        // then
        assertThat(reservation.getReservationStatus()).isEqualTo(ReservationStatus.RESERVATION);
        assertThat(reservation.getScreening().getMovie().getTitle()).isEqualTo(movie.getTitle());
        assertThat(reservation.getAudienceCount()).isEqualTo(audienceCount);
    }
}