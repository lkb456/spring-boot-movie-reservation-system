package com.example.movie_reservation.domain.seat;

import com.example.movie_reservation.domain.reservation.Reservation;
import com.example.movie_reservation.domain.reservation.ReservationStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ReservationStatus reservationStatus; // 예약 상태

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESERVATIONS_ID")
    private Reservation reservation;

    @Builder
    public Seat(Long id, Integer seatNumber, ReservationStatus reservationStatus, Reservation reservation) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.reservationStatus = reservationStatus;
        this.reservation = reservation;
    }

    public void reserve(Reservation reservation) {
        this.reservation = reservation;
        reservation.addSeat(this);
    }

    public void updateReserveStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public boolean isAvailable() {
        if (this.reservationStatus.equals(ReservationStatus.RESERVATION)) {
            return false;
        }

        return true;
    }
}
