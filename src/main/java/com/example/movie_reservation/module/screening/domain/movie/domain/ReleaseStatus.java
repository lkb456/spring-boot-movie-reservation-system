package com.example.movie_reservation.module.screening.domain.movie.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReleaseStatus {

    RELEASE("개봉"),
    UN_RELEASE("미개봉");

    private final String message;

    public boolean isRelease(ReleaseStatus releaseStatus) {
        return RELEASE.equals(releaseStatus);
    }
}
