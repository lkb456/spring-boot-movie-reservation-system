package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.Duration;

public class CreateDto {

    private static ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    public static ConsumerSaveAndUpdateRequestDto createConsumerSaveDto() {
        return ConsumerSaveAndUpdateRequestDto.builder()
                .nickname("대림동 불주먹")
                .phoneNumber("01012341234")
                .build();
    }

    public static MovieRequestDto.MovieUpdateDto createMovieUpdateDto() {
        return MovieRequestDto.MovieUpdateDto.builder()
                .title("아바타2")
                .fee(15000L)
                .runningTime(Duration.ofMinutes(240))
                .releaseStatus(ReleaseStatus.RELEASE)
                .build();
    }

    public static MovieDtoProjection createMovieDtoProjection() {
        MovieDtoProjection projection = factory.createProjection(MovieDtoProjection.class);
        projection.setTitle("아바타");
        projection.setFee(100000L);
        projection.setRunningTime(Duration.ofMinutes(120000L));
        projection.setReleaseStatus(ReleaseStatus.RELEASE);
        return projection;
    }
}
