package com.example.movie_reservation.service;

import com.example.movie_reservation.controller.seat.dto.SeatRequestDto;
import com.example.movie_reservation.domain.reservation.Reservation;
import com.example.movie_reservation.domain.reservation.ReservationStatus;
import com.example.movie_reservation.domain.seat.Seat;
import com.example.movie_reservation.domain.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public void saveSeat(SeatRequestDto seatRequestDto, Reservation reservation) {
        if (isExists(seatRequestDto.getSeatNumber())) {
            mergeSeat(seatRequestDto, reservation);

        } else {
            saveNewSeat(seatRequestDto, reservation);
        }
    }

    private boolean isExists(Integer seatNumber) {
        return seatRepository.existsBySeatNumber(seatNumber);
    }

    private void mergeSeat(SeatRequestDto seatRequestDto, Reservation reservation) {
        Seat seat = findSeat(seatRequestDto.getSeatNumber());

        if (!seat.isAvailable()) {
            throw new IllegalArgumentException("이미 예약된 좌석입니다.");
        }

        seat.reserve(reservation);
        seat.updateReserveStatus(ReservationStatus.RESERVATION);
    }

    private Seat saveNewSeat(SeatRequestDto seatRequestDto, Reservation reservation) {
        Seat seat = Seat.builder()
                .seatNumber(seatRequestDto.getSeatNumber())
                .reservationStatus(ReservationStatus.RESERVATION)
                .build();
        seat.reserve(reservation);
        return seatRepository.save(seat);
    }

    public Seat findSeat(Integer seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new IllegalArgumentException("좌석 정보가 존재하지 않습니다."));
    }
}
