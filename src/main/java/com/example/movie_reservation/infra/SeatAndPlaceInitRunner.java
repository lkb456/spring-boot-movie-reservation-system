package com.example.movie_reservation.infra;

import com.example.movie_reservation.service.PlaceService;
import com.example.movie_reservation.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeatAndPlaceInitRunner implements ApplicationRunner {

    private final SeatService seatService;
    private final PlaceService placeService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Seat and Place Init Start.....");
        seatService.initSeat();
        placeService.initPlace();
    }
}
