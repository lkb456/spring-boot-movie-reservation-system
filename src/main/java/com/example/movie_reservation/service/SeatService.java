package com.example.movie_reservation.service;

import com.example.movie_reservation.domain.reservation.ReservationStatus;
import com.example.movie_reservation.domain.seat.Seat;
import com.example.movie_reservation.domain.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;
    private final FileReaderService<Seat> fileReaderService;

    public void initSeat() {
        fileReaderService.fileReader();

        List<String> seatInfo = fileReaderService.getData();
        for (String info : seatInfo) {
            String[] seatRowAndColum = info.split(" - ");
            saveSeat(seatRowAndColum);
        }
    }

    private void saveSeat(String[] seatRowAndColum) {
        Seat seat = Seat.builder()
                .rowNumber(seatRowAndColum[0])
                .columNumber(Integer.valueOf(seatRowAndColum[1]))
                .reservationStatus(ReservationStatus.UN_RESERVATION)
                .build();
        seatRepository.save(seat);
    }

    public Seat findSeat(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("좌석 정보가 없습니다."));
    }
}
