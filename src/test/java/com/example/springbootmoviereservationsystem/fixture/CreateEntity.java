package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.seat.Seat;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationStatus;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreateEntity {

    public static Consumer createConsumer(Long id, String nickname, String phoneNumber) {
        return Consumer.builder()
                .id(id)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static Movie createMovie(Long id, String title, Long fee, Duration runningTime, ReleaseStatus status) {
        return Movie.builder()
                .title(title)
                .fee(fee)
                .runningTime(runningTime)
                .releaseStatus(status)
                .build();
    }

    public static Screening createScreening(Movie movie, LocalDateTime when) {
        return Screening.builder()
                .movie(movie)
                .whenScreened(when)
                .build();
    }

    public static Ticket createTicket(String title, int audienceCount, LocalDateTime when, boolean isPublish) {
        return Ticket.builder()
                .movieTitle(title)
                .audienceCount(audienceCount)
                .whenScreened(when)
                .isPublish(isPublish)
                .build();
    }

    public static Seat createSeat(String rowNumber, Integer columNumber, ReservationStatus reservationStatus, Reservation reservation) {
        return Seat.builder()
                .rowNumber(rowNumber)
                .columNumber(columNumber)
                .reservationStatus(reservationStatus)
                .reservation(reservation)
                .build();
    }

}
