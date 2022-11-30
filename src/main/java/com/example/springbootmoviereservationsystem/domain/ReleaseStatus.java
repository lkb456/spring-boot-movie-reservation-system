package com.example.springbootmoviereservationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReleaseStatus {

    RELEASE("개봉"),
    UN_RELEASE("미개봉");

    private final String message;

    public boolean isRelease(ReleaseStatus releaseStatus) {
        return RELEASE.equals(releaseStatus);
    }
}
