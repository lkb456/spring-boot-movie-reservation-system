package com.example.movie_reservation.service;

import com.example.movie_reservation.domain.place.Place;
import com.example.movie_reservation.infra.FilePath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceFileReaderServiceImpl implements FileReaderService<Place> {

    private List<String> placeData = new ArrayList<>();

    // TODO 좌석 정보를 어떻게 해야할지???
    //  - 엔티티로 만들어 관리할 것인가??
    //  - 내장객체로 만들어 관리할 것인가??
    //  - 좌석 번호만 내장객체로 만들고 좌석 엔티티로 관리할 것인가??
    @Override
    public void fileReader() {
        try (FileReader fileReader = new FileReader(FilePath.PLACE_FILE_FULL_PATH, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split(", ");
                for (String place : split) {
                    placeData.add(place);
                }
            }
        } catch (IOException e) {
            log.info("Place File Reader Exception !!! : {}", e.getMessage());
        }
    }

    @Override
    public List<String> getData() {
        return Collections.unmodifiableList(placeData);
    }
}
