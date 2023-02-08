package com.example.movie_reservation.module.main.service;

import com.example.movie_reservation.module.main.dto.SearchMovieOrActorResponseDto;
import com.example.movie_reservation.module.actor.domain.Actor;
import com.example.movie_reservation.module.actor.domain.ActorRepository;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.screening.domain.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public SearchMovieOrActorResponseDto searchMovieOrActor(String keyword) {
        List<Actor> actors = actorRepository.findByFirstNameContainingOrLastNameContainingOrderByFirstName(keyword);
        List<Movie> movies = movieRepository.findByTitleContainingOrderByTitle(keyword);
        return SearchMovieOrActorResponseDto.of(movies, actors);
    }

}
