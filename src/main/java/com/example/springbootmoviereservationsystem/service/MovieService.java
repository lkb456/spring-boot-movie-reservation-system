package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponsePageDto;
import com.example.springbootmoviereservationsystem.domain.actor.Actor;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.service.actor.ActorService;
import com.example.springbootmoviereservationsystem.util.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorService actorService;

    public Long saveMovie(MovieRequestDto movieSaveRequestDto) {
        Movie savedMovie = movieRepository.save(movieSaveRequestDto.toEntity());
        return savedMovie.getId();
    }

    @Transactional
    public void updateMovie(Long movieId, MovieRequestDto movieRequestDto) {
        Movie movie = findMovie(movieId);
        movie.updateInfo(
                movieRequestDto.getTitle(),
                Money.wons(movieRequestDto.getFee()),
                movieRequestDto.getRunningTime(),
                movieRequestDto.getReleaseStatus()
        );
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public Movie findMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }

    public MovieResponsePageDto searchMovies(String title, Pageable pageable) {
        Page<MovieResponseDto> result = movieRepository.findByTitleContainingOrderByTitle(title, pageable);
        return MovieResponsePageDto.of(result);
    }

    @Transactional
    public void movieActorAdd(Long actorId, Long movieId) {
        Actor actor = actorService.findActor(actorId);
        Movie movie = findMovie(movieId);
        movie.addActor(Set.of(actor));
    }
}
