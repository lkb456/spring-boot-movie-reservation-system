package com.example.springbootmoviereservationsystem.domain.service;

import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.fixture.CreateDto;
import com.example.springbootmoviereservationsystem.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.springbootmoviereservationsystem.fixture.CreateDto.createMovieDtoProjection;
import static com.example.springbootmoviereservationsystem.fixture.CreateEntity.createMovie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    @DisplayName("영화 조회하기")
    void findMovie() {
        Long id = 1L;
        Movie movie = createMovie();
        given(movieRepository.findById(any())).willReturn(Optional.of(movie));

        Movie findMovie = movieService.findMovie(id);

        assertThat(movie.getTitle()).isEqualTo(findMovie.getTitle());
        verify(movieRepository).findById(id);
    }

    @Test
    @DisplayName("영화 정보가 조회되지 않는 경우 예외")
    void findMovie_Exception() {
        given(movieRepository.findById(any())).willThrow(new IllegalArgumentException("존재하지 않는 영화입니다."));

        assertThatThrownBy(() -> movieService.findMovie(any()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 영화입니다.");

        verify(movieRepository).findById(any());
    }

    @Test
    @DisplayName("영화 정보 업데이트 하기")
    void updateMovie() {
        Long id = 1L;
        Movie movie = createMovie();
        given(movieRepository.findById(any())).willReturn(Optional.of(movie));

        MovieRequestDto.MovieUpdateDto dto = CreateDto.createMovieUpdateDto();
        movieService.updateMovie(id, dto);

        assertThat(dto.getTitle()).isEqualTo(movie.getTitle());
        assertThat(dto.getFee()).isEqualTo(movie.getFee().getAmount().longValue());

        verify(movieRepository).findById(id);
    }

    @Test
    @DisplayName("영화 정보 삭제하기")
    void deleteMovie() {
        Movie movie = createMovie();
        willDoNothing().given(movieRepository).deleteById(any());

        movieService.deleteMovie(movie.getId());
        assertThatThrownBy(() -> movieRepository.findById(movie.getId()).get())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");

        verify(movieRepository).deleteById(movie.getId());
    }

    @Test
    @DisplayName("영화 정보 페이지 타입으로 조회하기")
    void searchMovies() {
        MovieDtoProjection projection = createMovieDtoProjection();
        given(movieRepository.findByTitleLikeAndReleaseMovie(any(), any(), any())).willReturn(new PageImpl<>(List.of(projection)));

        MovieResponseDto.PageMovieDto result = movieService.searchMovies("title", ReleaseStatus.RELEASE, Pageable.unpaged());

        assertThat(result).isInstanceOf(MovieResponseDto.PageMovieDto.class);
        assertThat(projection.getTitle()).isEqualTo(result.getElements().get(0).getTitle());
        verify(movieRepository).findByTitleLikeAndReleaseMovie(any(), any(), any());
    }
}