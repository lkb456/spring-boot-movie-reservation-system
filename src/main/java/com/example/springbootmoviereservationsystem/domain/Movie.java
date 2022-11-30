package com.example.springbootmoviereservationsystem.domain;

public class Movie {

    private ReleaseStatus releaseStatus;
    public void changeReleaseStatus(ReleaseStatus releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public boolean isReleaseMovie() {
        return this.releaseStatus.isRelease(ReleaseStatus.RELEASE);
    }
}
