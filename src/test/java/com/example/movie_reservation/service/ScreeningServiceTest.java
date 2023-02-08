package com.example.movie_reservation.service;

import com.example.movie_reservation.module.screening.dto.PageScreenResponseDto;
import com.example.movie_reservation.module.screening.dto.ScreeningSaveResponseDto;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.screening.domain.movie.service.MovieService;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.module.screening.domain.ScreeningRepository;
import com.example.movie_reservation.fixture.CreateDto;
import com.example.movie_reservation.fixture.CreateEntity;
import com.example.movie_reservation.module.screening.service.ScreeningService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
class ScreeningServiceTest {

    @Mock
    private MovieService movieService;

    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    @Test
    @DisplayName("상영 정보 저장하기 테스트")
    void saveScreen() {
        // given
        Movie movie = CreateEntity.createMovie();
        given(movieService.findMovie(any())).willReturn(movie);

        Screening screening = CreateEntity.createScreening(movie);
        given(screeningRepository.save(any())).willReturn(screening);

        // when
        Screening result = screeningService.saveScreen(CreateDto.createScreeningSaveRequestDto());

        // then
        assertThat(screening.getWhenScreened()).isEqualTo(result.getWhenScreened());
        assertThat(movie).isEqualTo(result.getMovie());

        verify(movieService).findMovie(any());
        verify(screeningRepository).save(any());
    }

    @Test
    @DisplayName("상영 정보 찾기 테스트")
    void findScreen() {
        // given
        Screening screening = CreateEntity.createScreening(CreateEntity.createMovie());
        given(screeningRepository.findById(any())).willReturn(Optional.of(screening));

        // when
        Screening findScreening = screeningService.findScreen(1L);

        // then
        assertThat(screening).isEqualTo(findScreening);
        assertThat(screening.getMovie()).isEqualTo(findScreening.getMovie());

        verify(screeningRepository).findById(any());
    }

    @Test
    @DisplayName("상영 정보 검색 페이징 처리 테스트")
    void searchScreens() {
        // given
        ScreeningSaveResponseDto projection = CreateDto.createScreeningDtoProjection();
        given(screeningRepository
                .findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(any(), any(), any()))
                .willReturn(new PageImpl<>(List.of(projection)));

        // when
        PageScreenResponseDto result = screeningService
                .searchScreens("d", LocalDateTime.of(2022, 12, 12, 2, 00), Pageable.unpaged());

        // then
        assertThat(result).isInstanceOf(PageScreenResponseDto.class);

        verify(screeningRepository).findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(any(), any(), any());
    }
}