package com.example.springbootmoviereservationsystem.domain.reservation;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.infra.policy.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.springbootmoviereservationsystem.fixture.CreateEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReservationTest {

    @Autowired
    private DiscountPolicy discountPolicy;

    @Test
    @DisplayName("티켓 발행하기")
    void publishTicket() {
        // given
        Consumer consumer = createConsumer();
        Movie movie = createMovie();
        Screening screening = createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5, discountPolicy);

        // when
        Ticket ticket = reservation.publishTicket();
        consumer.receiveTicket(ticket);

        // then
        assertThat(ticket.getMovieTitle()).isEqualTo(movie.getTitle());
        assertThat(ticket.getAudienceCount()).isEqualTo(reservation.getAudienceCount());
        assertThat(consumer.hasTicket()).isTrue();
    }
}