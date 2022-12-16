package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.example.springbootmoviereservationsystem.fixture.CreateEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

class ReservationTest {

    @Test
    @DisplayName("티켓 발행하기")
    void publishTicket() {
        // given
        Consumer consumer = createConsumer(1L, "대림동 불주먹", "01012341234");
        Movie movie = createMovie(1L, "아바타", 10000L, Duration.ofMinutes(12000L), ReleaseStatus.RELEASE);
        Screening screening = createScreening(movie, LocalDateTime.of(2022,12,16,13,00));
        Reservation reservation = screening.reserve(consumer, 5);

        // when
        Ticket ticket = reservation.publishTicket();
        consumer.receiveTicket(ticket);

        // then
        assertThat(ticket.getMovieTitle()).isEqualTo(movie.getTitle());
        assertThat(ticket.getAudienceCount()).isEqualTo(reservation.getAudienceCount());
        assertThat(consumer.hasTicket()).isTrue();
    }
}