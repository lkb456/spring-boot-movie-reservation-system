package com.example.movie_reservation.module.reservation;

import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.reservation.domain.Reservation;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.module.ticket.domain.Ticket;
import com.example.movie_reservation.module.screening.policy.DiscountPolicy;
import com.example.movie_reservation.infra.util.Money;
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