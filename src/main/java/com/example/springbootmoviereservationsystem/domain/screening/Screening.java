package com.example.springbootmoviereservationsystem.domain.screening;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "screeningWithMovieWithMoney",
        attributeNodes = {
                @NamedAttributeNode(value = "movie", subgraph = "movieWithMoney")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "movieWithMoney",
                        attributeNodes = {
                                @NamedAttributeNode("fee")
                        }
                )
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
