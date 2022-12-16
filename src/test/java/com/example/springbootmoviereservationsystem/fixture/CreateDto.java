package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.controller.dto.consumer.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieRequestDto;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

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

    public static MovieDtoProjection createMovieDtoProjection() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        MovieDtoProjection projection = factory.createProjection(MovieDtoProjection.class);
        projection.setTitle("아바타");
        projection.setFee(100000L);
        projection.setRunningTime(Duration.ofMinutes(120000L));
        projection.setReleaseStatus(ReleaseStatus.RELEASE);
        return projection;
    }
}
