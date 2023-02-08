package com.example.movie_reservation.module.seat.service;

import com.example.movie_reservation.module.seat.dto.SeatRequestDto;
import com.example.movie_reservation.module.reservation.domain.Reservation;
import com.example.movie_reservation.module.seat.domain.Seat;
import com.example.movie_reservation.module.seat.domain.SeatRepository;
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
        Seat seat = Seat.builder()
                .seatNumber(seatRequestDto.getSeatNumber())
                .build();
        seat.reserve(reservation);
        seatRepository.save(seat);
    }

    public Seat findSeat(Integer seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new IllegalArgumentException("좌석 정보가 존재하지 않습니다."));
    }
}
