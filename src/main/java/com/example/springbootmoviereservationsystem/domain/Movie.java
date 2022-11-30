package com.example.springbootmoviereservationsystem.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Movie {

    private Long fee;
    private ReleaseStatus releaseStatus;

    public void changeReleaseStatus(ReleaseStatus releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public boolean isReleaseMovie() {
        return this.releaseStatus.isRelease(ReleaseStatus.RELEASE);
    }

    public Long calculateMovieFee(int audienceCount) {
        return multiply(audienceCount);
    }

    private Long multiply(int audienceCount) {
        return this.fee * audienceCount;
    }
}
