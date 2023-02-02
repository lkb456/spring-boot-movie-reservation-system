package com.example.movie_reservation.domain.reservation;

import com.example.movie_reservation.domain.consumer.Consumer;
import com.example.movie_reservation.domain.screening.Screening;
import com.example.movie_reservation.domain.seat.Seat;
import com.example.movie_reservation.domain.ticket.Ticket;
import com.example.movie_reservation.util.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "reserveWithAll",
        attributeNodes = {
                @NamedAttributeNode("consumer"),
                @NamedAttributeNode(value = "screening", subgraph = "screeningWithMovie"),
                @NamedAttributeNode("seats")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "screeningWithMovie",
                        attributeNodes = {
                                @NamedAttributeNode("movie")
                        }
                )
        }
)
@Getter
@Entity
@Table(name = "RESERVATIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATIONS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONSUMERS_ID")
    private Consumer consumer; // 고객 정보

    @Column(name = "AUDIENCE_COUNT")
    private int audienceCount; // 고객 인원 수

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SCREENS_ID")
    private Screening screening; // 상영 정보

    @Column(name = "FEE")
    private BigDecimal fee; // 예매 요금

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<Seat> seats = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @CreationTimestamp
    @Column(name = "RESERVE_TIME")
    private LocalDateTime reserveTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Builder
    public Reservation(Consumer consumer, int audienceCount, Screening screening, Money fee, ReservationStatus reservationStatus) {
        this.consumer = consumer;
        this.audienceCount = audienceCount;
        this.screening = screening;
        this.fee = fee.getAmount();
        this.reservationStatus = reservationStatus;
    }

    public Ticket publishTicket() {
        return Ticket.builder()
                .movieTitle(screening.getMovie().getTitle())
                .audienceCount(audienceCount)
                .isPublish(true)
                .whenScreened(screening.getWhenScreened())
                .build();
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public void cancel() {
        if (this.consumer.hasTicket()) {
            throw new IllegalArgumentException("티켓 발행 이후 취소는 안됩니다!");
        }

        this.consumer.cancelTicket();
        this.reservationStatus = ReservationStatus.CANCELLED_BY_CUSTOMER;
    }
}