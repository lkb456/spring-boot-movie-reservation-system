package com.example.springbootmoviereservationsystem.domain.reservation;

import com.example.springbootmoviereservationsystem.domain.money.Money;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.seat.Seat;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "reservationWithConsumerWithScreeningWithMovieWithMoneyWithSeats",
        attributeNodes = {
                @NamedAttributeNode("consumer"),
                @NamedAttributeNode(value = "screening", subgraph = "screening-entity-graph"),
                @NamedAttributeNode("seats"),
                @NamedAttributeNode("fee")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "screening-entity-graph",
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Money fee; // 예매 요금

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Builder
    public Reservation(Consumer consumer, int audienceCount, Screening screening, Money fee, ReservationStatus reservationStatus) {
        this.consumer = consumer;
        this.audienceCount = audienceCount;
        this.screening = screening;
        this.fee = fee;
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

        for (Seat seat : seats) {
            seat.updateReserveStatus(ReservationStatus.CANCELLED_BY_CUSTOMER);
        }
    }
}