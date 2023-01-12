package com.example.movie_reservation.domain.seat;

import com.example.movie_reservation.domain.consumer.Consumer;
import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.reservation.Reservation;
import com.example.movie_reservation.domain.screening.Screening;
import com.example.movie_reservation.infra.policy.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.movie_reservation.fixture.CreateEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

class SeatTest {

    private DiscountPolicy discountPolicy;

    @Test
    @DisplayName("좌석 예매 하기")
    void seat_reserve() {
        // given
        Consumer consumer = createConsumer();
        Movie movie = createMovie();
        Screening screening = createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5, Money.ZERO);
        Seat seat = createSeatForReservation(reservation);

        // when
        seat.reserve(reservation);

        // then
        assertThat(seat.getColumNumber()).isEqualTo(reservation.getSeats().get(0).getColumNumber());
        assertThat(seat.getRowNumber()).isEqualTo(reservation.getSeats().get(0).getRowNumber());

    }
}