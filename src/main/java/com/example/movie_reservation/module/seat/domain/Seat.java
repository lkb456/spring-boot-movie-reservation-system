package com.example.movie_reservation.module.seat.domain;

import com.example.movie_reservation.module.reservation.domain.Reservation;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SEATS")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEATS_ID")
    private Long id;

    @Column(name = "SEAT_NUMBER")
    private Integer seatNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESERVATIONS_ID")
    private Reservation reservation;

    @Builder
    public Seat(Long id, Integer seatNumber, Reservation reservation) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.reservation = reservation;
    }

    public void reserve(Reservation reservation) {
        this.reservation = reservation;
        reservation.addSeat(this);
    }
}
