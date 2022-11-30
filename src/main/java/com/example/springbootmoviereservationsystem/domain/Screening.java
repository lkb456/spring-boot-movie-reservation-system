package com.example.springbootmoviereservationsystem.domain;

import java.util.ArrayList;
import java.util.List;

public class Screening {

    private List<Movie> movies = new ArrayList<>();

    public void addReleaseMovie(List<Movie> movies) {
        // 영화 리스트를 순회 한다.
        for (Movie movie : movies) {
            // 영화 상태가 개봉인 상태만 저장한다.
            if (movie.isReleaseMovie()) {
                this.movies.add(movie);
            }
        }
    }

    // 고객정보, 인원수, 영화 순번을 입력 받아 영화를 예매한다.
    public Reservation reserve(Consumer consumer, int audienceCount, int movieSequence) {
        Movie movie = pickMovie(movieSequence);
        return Reservation.of(consumer, audienceCount, movie, movie.calculateMovieFee(audienceCount));
    }

    // 영화를 가져온다.
    private Movie pickMovie(int movieSequence) {
        return this.movies.get(movieSequence);
    }
}
