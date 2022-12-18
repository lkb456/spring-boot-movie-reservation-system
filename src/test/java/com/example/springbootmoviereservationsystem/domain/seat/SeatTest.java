package com.example.springbootmoviereservationsystem.domain.seat;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.springbootmoviereservationsystem.fixture.CreateEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

class SeatTest {

    @Test
    @DisplayName("좌석 예매 하기")
    void seat_reserve() {
        // given
        Consumer consumer = createConsumer();
        Movie movie = createMovie();
        Screening screening = createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5);
        Seat seat = createSeatForReservation(reservation);

        // when
        seat.reserve(reservation);

        // then
        assertThat(seat.getColumNumber()).isEqualTo(reservation.getSeats().get(0).getColumNumber());
        assertThat(seat.getRowNumber()).isEqualTo(reservation.getSeats().get(0).getRowNumber());

    }
}