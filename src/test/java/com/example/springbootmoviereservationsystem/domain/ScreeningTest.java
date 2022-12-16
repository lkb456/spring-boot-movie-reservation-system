package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import com.example.springbootmoviereservationsystem.domain.type.ReservationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static com.example.springbootmoviereservationsystem.domain.CreateEntity.*;
import static com.example.springbootmoviereservationsystem.domain.CreateEntity.createConsumer;

class ScreeningTest {

    @Test
    @DisplayName("예매 정보 생성 하기")
    void reserve() {
        // given
        Consumer consumer = createConsumer(1L, "대림동 불주먹", "01012341234");
        Movie movie = createMovie("싸움의 기술", 1000L, Duration.ofMinutes(120000), ReleaseStatus.RELEASE);
        Screening screening = createScreening(movie, LocalDateTime.of(2022,12,16,13,00));

        // when
        int audienceCount = 5;
        Reservation reservation = screening.reserve(consumer, audienceCount);

        // then
        assertThat(reservation.getReservationStatus()).isEqualTo(ReservationStatus.RESERVATION);
        assertThat(reservation.getScreening().getMovie().getTitle()).isEqualTo(movie.getTitle());
        assertThat(reservation.getAudienceCount()).isEqualTo(audienceCount);
    }
}