package com.example.movie_reservation.fixture;

import com.example.movie_reservation.controller.consumer.dto.ConsumerRequestDto;
import com.example.movie_reservation.controller.movie.dto.MovieRequestDto;
import com.example.movie_reservation.controller.movie.dto.MovieResponseDto;
import com.example.movie_reservation.controller.reservation.dto.ReservationSaveRequestDto;
import com.example.movie_reservation.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.movie_reservation.controller.screening.dto.ScreeningSaveResponseDto;
import com.example.movie_reservation.controller.seat.dto.SeatRequestDto;
import com.example.movie_reservation.domain.movie.ReleaseStatus;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateDto {

    private static ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    public static ConsumerRequestDto createConsumerSaveDto() {
        return ConsumerRequestDto.builder()
                .nickname("대림동 불주먹")
                .phoneNumber("01012341234")
                .build();
    }

    public static MovieRequestDto createMovieRequestDto() {
        return MovieRequestDto.builder()
                .title("아바타2")
                .fee(15000L)
                .runningTime(Duration.ofMinutes(240))
                .releaseStatus(ReleaseStatus.RELEASE)
                .build();
    }

    public static List<MovieResponseDto> createMovieResponseDtos() {
        List<MovieResponseDto> list = new ArrayList<>();
        for (int count = 1; count <= 10; count++) {
            list.add(MovieResponseDto.of(CreateEntity.createMovie()));
        }

        return list;
    }

    public static ScreeningSaveRequestDto createScreeningSaveRequestDto() {
        return ScreeningSaveRequestDto.builder()
                .movieId(1L)
                .when(LocalDateTime.of(2022, 12, 01, 8, 00))
                .build();
    }

    public static ScreeningSaveResponseDto createScreeningDtoProjection() {
        return ScreeningSaveResponseDto.of(CreateEntity.createScreening(CreateEntity.createMovie()));
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
                .audienceCount(1)
                .seatSaveRequestDto(List.of(
                        SeatRequestDto.builder().seatId(1L).build()))
                .build();
    }
}
