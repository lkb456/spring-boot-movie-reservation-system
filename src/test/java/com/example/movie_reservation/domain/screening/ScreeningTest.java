package com.example.movie_reservation.domain.screening;

import com.example.movie_reservation.domain.consumer.Consumer;
import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.reservation.Reservation;
import com.example.movie_reservation.domain.reservation.ReservationStatus;
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