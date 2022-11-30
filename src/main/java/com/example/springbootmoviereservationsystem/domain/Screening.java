package com.example.springbootmoviereservationsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screening {

    @Id
    private Long id;

    @ManyToOne
    private Movie movie;
    private LocalDateTime whenScreened;

    public Reservation reserve(Consumer consumer, int audienceCount, int movieSequence) {
        if (movie.isReleaseMovie()) {
            return Reservation.builder()
                    .consumer(consumer)
                    .audienceCount(audienceCount)
                    .screening(this)
                    .fee(calculateFee(movieSequence))
                    .build();
        }

        throw new IllegalArgumentException("Movie is UnRelease Exception !!");
    }

    private Long calculateFee(int movieSequence) {
        return movie.calculateMovieFee(movieSequence);
    }
}
