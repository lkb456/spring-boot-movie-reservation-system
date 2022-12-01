package com.example.springbootmoviereservationsystem.domain.screening;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    private LocalDateTime whenScreened;

    public Reservation reserve(Consumer consumer, int audienceCount) {
        if (movie.isReleaseMovie()) {
            return Reservation.builder()
                    .consumer(consumer)
                    .audienceCount(audienceCount)
                    .screening(this)
                    .fee(calculateFee(audienceCount))
                    .build();
        }

        throw new IllegalArgumentException("Movie is UnRelease Exception !!");
    }

    private Long calculateFee(int audienceCount) {
        return movie.calculateMovieFee(audienceCount);
    }
}
