package com.example.springbootmoviereservationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SEATS")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEATS_ID")
    private Long id;

    private String rowNumber; // 행

    private Integer columNumber; // 열 번호

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATIONS_ID")
    private Reservation reservation;

    public void reserve(Reservation reservation) {
        if (this.reservation != null) {
            this.reservation.getSeats().remove(this);
        }

        this.reservation = reservation;
        reservation.addSeat(this);
    }
}
