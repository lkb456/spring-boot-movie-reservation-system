package com.example.movie_reservation.domain.screening;

import com.example.movie_reservation.domain.consumer.Consumer;
import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.reservation.Reservation;
import com.example.movie_reservation.domain.reservation.ReservationStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "screeningWithMovie",
        attributeNodes = {
                @NamedAttributeNode(value = "movie")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Screening(Movie movie, LocalDateTime whenScreened) {
        this.movie = movie;
        this.whenScreened = whenScreened;
    }

    public Reservation reserve(Consumer consumer, int audienceCount, Money discountMoney) {
        if (!movie.isReleaseMovie()) {
            throw new IllegalArgumentException("Movie is UnRelease Exception !!");
        }

        return Reservation.builder()
                .consumer(consumer)
                .audienceCount(audienceCount)
                .screening(this)
                .fee(calculateFee(audienceCount, discountMoney))
                .reservationStatus(ReservationStatus.RESERVATION)
                .build();
    }

    private Money calculateFee(int audienceCount, Money disCountMoney) {
        return movie.calculateMovieFee(disCountMoney).times(audienceCount);
    }

    public Money getMovieFee() {
        return Money.builder().amount(movie.getFee()).build();
    }
}
