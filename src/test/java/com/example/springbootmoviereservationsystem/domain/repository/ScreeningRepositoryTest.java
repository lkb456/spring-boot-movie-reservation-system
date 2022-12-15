package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.domain.Consumer;
import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.Screening;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
class ScreeningRepositoryTest {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @Test
    public void test() {
        Consumer consumer = saveConsumer();
        Movie movie = saveMovie();
        Screening screening = saveScreening(movie);

        String title = "영";
        LocalDateTime when = LocalDateTime.of(2022, 12, 16, 14, 00);

    }

    private Screening saveScreening(Movie movie) {
        Screening screening = Screening.builder()
                .movie(movie)
                .whenScreened(LocalDateTime.of(2022, 12, 16, 13, 00))
                .build();
        return screeningRepository.save(screening);
    }

    private Consumer saveConsumer() {
        Consumer consumer = Consumer.builder()
                .nickname("고객")
                .phoneNumber("01012341234")
                .build();
        return consumerRepository.save(consumer);
    }

    private Movie saveMovie() {
        Movie movie = Movie.builder()
                .title("영화")
                .fee(20000L)
                .releaseStatus(ReleaseStatus.RELEASE)
                .runningTime(Duration.ofMinutes(12000))
                .build();
        Movie savedMovie = movieRepository.save(movie);
        return movie;
    }
}