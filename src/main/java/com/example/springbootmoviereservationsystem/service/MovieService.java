package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.money.Money;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.infra.policy.AmountDiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;
    private final AmountDiscountPolicy discountPolicy;

    // 할인 금액 반영 안됨
    // TODO money 엔티티를 value 타입으로 변경
    // money 는 value 타입이 적절하다
    public Long saveMovie(MovieRequestDto.MovieSaveDto movieSaveRequestDto) {
        Movie movie = movieSaveRequestDto.toEntity();
        movie.changeDiscountPolicy(discountPolicy);
        return movieRepository.save(movie).getId();
    }

    @Transactional
    public void updateMovie(Long movieId, MovieRequestDto.MovieUpdateDto movieUpdateRequestDto) {
        Movie movie = findMovie(movieId);
        movie.updateInfo(movieUpdateRequestDto.getTitle(),
                Money.wons(movieUpdateRequestDto.getFee()),
                movieUpdateRequestDto.getRunningTime(),
                movieUpdateRequestDto.getReleaseStatus());
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public Movie findMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }

    public MovieResponseDto.PageMovieDto searchMovies(String title, ReleaseStatus status, Pageable pageable) {
        Page<MovieDtoProjection> result = movieRepository.findByTitleLikeAndReleaseMovie(title, status, pageable);
        return MovieResponseDto.PageMovieDto.of(result);
    }
}
