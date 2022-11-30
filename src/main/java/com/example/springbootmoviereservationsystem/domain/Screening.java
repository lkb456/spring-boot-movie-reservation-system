package com.example.springbootmoviereservationsystem.domain;

import java.util.ArrayList;
import java.util.List;

public class Screening {

    private List<Movie> movies = new ArrayList<>();

    public void screeningMovie(List<Movie> movies) {
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
        // 순번에 맞는 영화를 가져온다.
        Movie movie = pickMovie(movieSequence);
        // 인원수에 대해 요금을 계산하고 예매 정보를 만든다.
        return Reservation.of(consumer, audienceCount, movie, movie.calculateMovieFee(audienceCount));
    }

    private Movie pickMovie(int movieSequence) {
        return this.movies.get(movieSequence);
    }
}
