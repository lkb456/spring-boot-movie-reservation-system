package com.example.springbootmoviereservationsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatFileReaderService {

    private static final String POSITION_FILE_FULL_PATH = "src/main/resources/static/seat/position.txt";
    private List<String[]> seatWords = new ArrayList<>();

    public List<String[]> readerResult() {
        filerReader();
        return Collections.unmodifiableList(seatWords);
    }

    private void filerReader() {
        try (FileReader fileReader = new FileReader(POSITION_FILE_FULL_PATH, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(fileReader)) {

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split(", ");
                seatWords.add(split);
            }
        } catch (IOException e) {
            //TODO 예외 message 처리
        }
    }
}
