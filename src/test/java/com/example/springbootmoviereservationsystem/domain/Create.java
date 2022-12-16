package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;

import java.time.Duration;
import java.time.LocalDateTime;

public class Create {
    public static Consumer createConsumer(String nickname, String phoneNumber) {
        return Consumer.builder()
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static Movie createMovie(String title, Long fee, Duration runningTime, ReleaseStatus status) {
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
}
