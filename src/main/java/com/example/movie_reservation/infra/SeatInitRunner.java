package com.example.movie_reservation.infra;

import com.example.movie_reservation.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeatInitRunner implements ApplicationRunner {

    private final SeatService seatService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Seat Init Start.....");
        seatService.initSeat();
    }
}
