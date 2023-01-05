package com.example.springbootmoviereservationsystem.domain.seat;

import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedEntityGraph(
        name = "seatWithReservation",
        attributeNodes = {
                @NamedAttributeNode(value = "reservation", subgraph = "reservationWithSeat"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "reservationWithSeat",
                        attributeNodes = {
                                @NamedAttributeNode("seats")
                        }
                )
        }
)
@Getter
@Entity
@Table(name = "SEATS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Seat(Long id, String rowNumber, Integer columNumber, ReservationStatus reservationStatus, Reservation reservation) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.columNumber = columNumber;
        this.reservationStatus = reservationStatus;
        this.reservation = reservation;
    }

    public void reserve(Reservation reservation) {
        if (this.reservation != null) {
            this.reservation.getSeats().remove(this);
        }

        this.reservation = reservation;
        reservation.addSeat(this);
    }

    public void updateReserveStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
