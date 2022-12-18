package com.example.springbootmoviereservationsystem.domain.reservation;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.springbootmoviereservationsystem.fixture.CreateEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

class ReservationTest {

    @Test
    @DisplayName("티켓 발행하기")
    void publishTicket() {
        // given
        Consumer consumer = createConsumer();
        Movie movie = createMovie();
        Screening screening = createScreening(movie);
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