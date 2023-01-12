package com.example.movie_reservation.service;

import com.example.movie_reservation.domain.place.Place;
import com.example.movie_reservation.domain.place.PlaceRepository;
import com.example.movie_reservation.domain.seat.Seat;
import com.example.movie_reservation.domain.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final FileReaderService<Place> fileReaderService;
    private final SeatRepository seatRepository;

    @Transactional
    public void initPlace() {
        fileReaderService.fileReader();

        Set<Seat> seats = seatRepository.findAll().stream().collect(Collectors.toSet());
        List<String> placeInfo = fileReaderService.getData();
        for (String info : placeInfo) {
            Place place = Place.builder().name(info).build();
            place.addSeat(seats);
            placeRepository.save(place);
        }
    }
}
