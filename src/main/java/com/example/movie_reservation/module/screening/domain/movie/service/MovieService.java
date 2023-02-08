package com.example.movie_reservation.module.screening.domain.movie.service;

import com.example.movie_reservation.infra.util.Money;
import com.example.movie_reservation.module.actor.domain.Actor;
import com.example.movie_reservation.module.actor.service.ActorService;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.screening.domain.movie.domain.MovieRepository;
import com.example.movie_reservation.module.screening.domain.movie.dto.MovieRequestDto;
import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponseDto;
import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponsePageDto;
import com.example.movie_reservation.exception.NotFoundEntityException;
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

    public Movie findMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(NotFoundEntityException::new);
    }

    public MovieResponsePageDto searchMovies(String title, Pageable pageable) {
        Page<MovieResponseDto> result = movieRepository.findByTitleContainingOrderByTitle(title, pageable);
        return MovieResponsePageDto.of(result);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void movieActorAdd(Long actorId, Long movieId) {
        Actor actor = actorService.findActor(actorId);
        Movie movie = findMovie(movieId);
        movie.addActor(Set.of(actor));
    }
}
