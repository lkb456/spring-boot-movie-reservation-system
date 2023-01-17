package com.example.movie_reservation.domain.reservation;

import com.example.movie_reservation.domain.consumer.Consumer;
import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.screening.Screening;
import com.example.movie_reservation.domain.ticket.Ticket;
import com.example.movie_reservation.infra.policy.DiscountPolicy;
import com.example.movie_reservation.util.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.movie_reservation.fixture.CreateEntity.*;
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
        Reservation reservation = screening.reserve(consumer, 5, Money.ZERO);

        // when
        Ticket ticket = reservation.publishTicket();
        consumer.receiveTicket(ticket);

        // then
        assertThat(ticket.getMovieTitle()).isEqualTo(movie.getTitle());
        assertThat(ticket.getAudienceCount()).isEqualTo(reservation.getAudienceCount());
        assertThat(consumer.hasTicket()).isTrue();
    }
}