package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.controller.dto.consumer.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieRequestDto;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;

import java.time.Duration;

public class CreateDto {

    public static ConsumerSaveAndUpdateRequestDto createConsumerSaveDto(String nickname, String phoneNumber) {
        return ConsumerSaveAndUpdateRequestDto.builder()
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static MovieRequestDto.MovieUpdateDto createMovieUpdateDto(String title, Long fee, Duration runningTime, ReleaseStatus releaseStatus) {
        return MovieRequestDto.MovieUpdateDto.builder()
                .title(title)
                .fee(fee)
                .runningTime(runningTime)
                .releaseStatus(releaseStatus)
                .build();
    }
}
