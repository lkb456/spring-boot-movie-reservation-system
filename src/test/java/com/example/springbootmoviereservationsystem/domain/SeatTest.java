package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import com.example.springbootmoviereservationsystem.domain.type.ReservationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static com.example.springbootmoviereservationsystem.fixture.CreateEntity.*;

class SeatTest {

    @Test
    @DisplayName("좌석 예매 하기")
    void seat_reserve() {
        // given
        Consumer consumer = createConsumer(1L, "대림동 불주먹", "01012341234");
        Movie movie = createMovie("싸움의 기술", 1000L, Duration.ofMinutes(120000), ReleaseStatus.RELEASE);
        Screening screening = createScreening(movie, LocalDateTime.of(2022,12,16,13,00));
        Reservation reservation = screening.reserve(consumer, 5);
        Seat seat = createSeat("A열", 13, ReservationStatus.RESERVATION, reservation);

        // when
        seat.reserve(reservation);

        // then
        assertThat(seat.getColumNumber()).isEqualTo(reservation.getSeats().get(0).getColumNumber());
        assertThat(seat.getRowNumber()).isEqualTo(reservation.getSeats().get(0).getRowNumber());

    }
}