package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReservationStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "SCREENS")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCREENS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Movie movie;

    @Column(name = "WHEN_SCREENED")
    private LocalDateTime whenScreened;

    public Reservation reserve(Consumer consumer, int audienceCount) {
        if (movie.isReleaseMovie()) {
            return Reservation.builder()
                    .consumer(consumer)
                    .audienceCount(audienceCount)
                    .screening(this)
                    .fee(calculateFee(audienceCount))
                    .reservationStatus(ReservationStatus.RESERVATION)
                    .build();
        }

        throw new IllegalArgumentException("Movie is UnRelease Exception !!");
    }

    private Long calculateFee(int audienceCount) {
        return movie.calculateMovieFee(audienceCount);
    }
}
