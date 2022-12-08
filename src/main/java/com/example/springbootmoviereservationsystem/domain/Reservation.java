package com.example.springbootmoviereservationsystem.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "RESERVATIONS")
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
    private Long fee; // 예매 요금

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Seat> seats = new ArrayList<>();

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
}