package com.example.movie_reservation.fixture;

import com.example.movie_reservation.module.actor.domain.Actor;
import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.screening.domain.movie.domain.ReleaseStatus;
import com.example.movie_reservation.module.reservation.domain.Reservation;
import com.example.movie_reservation.module.reservation.domain.ReservationStatus;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.module.seat.domain.Seat;
import com.example.movie_reservation.module.ticket.domain.Ticket;
import com.example.movie_reservation.infra.util.Money;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateEntity {

    public static Actor createActor() {
        return Actor.builder()
                .firstName("서")
                .lastName("인국")
                .image("이미지")
                .birthDate(LocalDate.MIN)
                .content("내용")
                .build();
    }

    public static Consumer createConsumer() {
        return Consumer.builder()
                .id(1L)
                .nickname("대림동 불주먹")
                .phoneNumber("01012341234")
                .build();
    }

    public static Movie createMovie() {
        return Movie.builder()
                .id(1L)
                .title("아바타")
                .amount(Money.wons(10000))
                .runningTime(Duration.ofMinutes(240))
                .releaseStatus(ReleaseStatus.RELEASE)
                .build();
    }

    public static Screening createScreening(Movie movie) {
        return Screening.builder()
                .movie(movie)
                .whenScreened(LocalDateTime.of(2022, 12, 01, 8, 00))
                .build();
    }

    public static Ticket createPublishTicket() {
        return Ticket.builder()
                .movieTitle("아바타")
                .audienceCount(5)
                .whenScreened(LocalDateTime.of(2022, 12, 01, 8, 00))
                .isPublish(true)
                .build();
    }

    public static Seat createSeatForReservation(Reservation reservation) {
        return Seat.builder()
                .seatNumber(1)
                .reservationStatus(ReservationStatus.RESERVATION)
                .reservation(reservation)
                .build();
    }

    public static Seat createSingleSeat(int number) {
        return Seat.builder()
                .seatNumber(number)
                .reservationStatus(ReservationStatus.UN_RESERVATION)
                .build();
    }
}
