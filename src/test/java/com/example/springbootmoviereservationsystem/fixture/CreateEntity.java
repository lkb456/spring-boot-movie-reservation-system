package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.domain.actor.Actor;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.seat.Seat;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationStatus;

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
                .rowNumber("A열")
                .columNumber(1)
                .reservationStatus(ReservationStatus.RESERVATION)
                .reservation(reservation)
                .build();
    }

    public static Seat createInitSeat(String[] seatRowAndColum) {
        return Seat.builder()
                .rowNumber(seatRowAndColum[0])
                .columNumber(Integer.valueOf(seatRowAndColum[1]))
                .reservationStatus(ReservationStatus.UN_RESERVATION)
                .build();
    }

    public static Seat createSingleSeat(int number) {
        return Seat.builder()
                .rowNumber("A열")
                .columNumber(number)
                .reservationStatus(ReservationStatus.UN_RESERVATION)
                .build();
    }
}
