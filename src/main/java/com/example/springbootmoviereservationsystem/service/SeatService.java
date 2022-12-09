package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.domain.Seat;
import com.example.springbootmoviereservationsystem.domain.repository.SeatRepository;
import com.example.springbootmoviereservationsystem.domain.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SeatService {

    private static final String POSITION_FILE_FULL_PATH = "src/main/resources/static/seat/position.txt";
    private final SeatRepository seatRepository;

    public void initSeat() {
        try (FileReader fileReader = new FileReader(POSITION_FILE_FULL_PATH, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(fileReader)) {

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split(", ");

                for (String data : split) {
                    String[] elementArray = data.split(" - ");

                    Seat seat = Seat.builder()
                            .rowNumber(elementArray[0])
                            .columNumber(Integer.valueOf(elementArray[1]))
                            .reservationStatus(ReservationStatus.UN_RESERVATION)
                            .build();
                    seatRepository.save(seat);
                }
            }
        } catch (IOException e) {
            //TODO 예외 message 처리
        }
    }

}
