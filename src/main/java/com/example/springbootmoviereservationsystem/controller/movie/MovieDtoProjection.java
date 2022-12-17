package com.example.springbootmoviereservationsystem.controller.movie;

import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;

import java.time.Duration;

public interface MovieDtoProjection {

    String getTitle(); // 제목
    Long getFee(); // 요금
    Duration getRunningTime(); // 영화시간
    ReleaseStatus getReleaseStatus(); // 개봉상태

    void setTitle(String title);
    void setFee(Long fee);
    void setRunningTime(Duration runningTime);
    void setReleaseStatus(ReleaseStatus releaseStatus);
}
