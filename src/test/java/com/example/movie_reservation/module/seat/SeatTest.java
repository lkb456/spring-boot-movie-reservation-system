package com.example.movie_reservation.module.seat;

import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.reservation.domain.Reservation;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.module.screening.policy.DiscountPolicy;
import com.example.movie_reservation.module.seat.domain.Seat;
import com.example.movie_reservation.infra.util.Money;
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
        assertThat(seat.getSeatNumber()).isEqualTo(reservation.getSeats().get(0).getSeatNumber());
        assertThat(seat.getSeatNumber()).isEqualTo(reservation.getSeats().get(0).getSeatNumber());

    }
}