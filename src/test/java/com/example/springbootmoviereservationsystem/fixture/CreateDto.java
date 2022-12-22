package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.reservation.dto.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreenDtoProjection;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.seat.SeatDto;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ScreeningSaveRequestDto createScreeningSaveRequestDto() {
        return ScreeningSaveRequestDto.builder()
                .movieId(1L)
                .when(LocalDateTime.of(2022, 12, 01, 8, 00))
                .build();
    }

    public static ScreenDtoProjection createScreeningDtoProjection() {
        ScreenDtoProjection projection = factory.createProjection(ScreenDtoProjection.class);
        projection.setMovie(CreateEntity.createMovie());
        projection.setWhenScreened(LocalDateTime.of(2022, 12, 12, 8, 00));
        return projection;
    }

    public static List<String[]> createFileContent(Path path) throws IOException {
        List<String> contents = Files.readAllLines(path);
        List<String[]> list = contents.stream()
                .map(data -> data.split(", "))
                .collect(Collectors.toList());
        return list;
    }

    public static ReservationSaveRequestDto CreateReservationSaveRequestDto() {
        return ReservationSaveRequestDto.builder()
                .screeningId(1L)
                .consumerId(1L)
                .audienceCount(5)
                .seatSaveRequestDto(List.of(
                        SeatDto.SaveRequestDto.builder().seatId(1L).build(),
                        SeatDto.SaveRequestDto.builder().seatId(2L).build(),
                        SeatDto.SaveRequestDto.builder().seatId(3L).build(),
                        SeatDto.SaveRequestDto.builder().seatId(4L).build(),
                        SeatDto.SaveRequestDto.builder().seatId(5L).build()))
                .build();
    }
}
