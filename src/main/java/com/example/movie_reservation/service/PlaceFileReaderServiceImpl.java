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
