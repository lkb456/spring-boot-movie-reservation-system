package com.example.movie_reservation.module.screening;

import com.example.movie_reservation.module.screening.dto.ScreeningSaveResponseDto;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.screening.domain.movie.domain.MovieRepository;
import com.example.movie_reservation.fixture.CreateEntity;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.module.screening.domain.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ScreeningRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void beforeEach() {
        Movie movie = movieRepository.save(CreateEntity.createMovie());
        screeningRepository.save(CreateEntity.createScreening(movie));
    }

    @Test
    @DisplayName("페이지 처리 쿼리 메서드 테스트")
    void ScreeningRepositoryTest() {
        // given
        String title = "아";
        LocalDateTime when = LocalDateTime.of(2022, 12, 01, 8, 00);
        Pageable pageable = Pageable.unpaged();

        Screening screening = screeningRepository.findById(1L).get();

        // when
        Page<ScreeningSaveResponseDto> result = screeningRepository.findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(title, when, pageable);

        // then
        List<ScreeningSaveResponseDto> content = result.getContent();
        assertNotNull(result);
        assertThat(screening.getWhenScreened()).isEqualTo(content.get(0).getWhenScreened());
    }

    @Test
    @DisplayName("")
    void findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual() {
        String title = "아";
        LocalDateTime when = LocalDateTime.of(2022, 12, 01, 8, 00);
        Pageable pageable = Pageable.unpaged();

        Page<ScreeningSaveResponseDto> result = screeningRepository
                .findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(
                        title,
                        when,
                        pageable
                );

        System.out.println(result);
    }
}