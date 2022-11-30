package com.example.springbootmoviereservationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class Screening {

    private Long screeningId;
    private Movie movie;
    private LocalDateTime whenScreened;

    public Reservation reserve(Consumer consumer, int audienceCount, int movieSequence) {
        if (movie.isReleaseMovie()) {
            return Reservation.of(consumer, audienceCount, this, calculateFee(movieSequence));
        }

        throw new IllegalArgumentException("Movie is UnRelease Exception !!");
    }

    private Long calculateFee(int movieSequence) {
        return movie.calculateMovieFee(movieSequence);
    }
}
