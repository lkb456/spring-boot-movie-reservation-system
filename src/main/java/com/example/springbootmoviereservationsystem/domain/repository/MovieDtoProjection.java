package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;

import java.time.Duration;

public interface MovieDtoProjection {

    String getTitle(); // 제목
    Long getFee(); // 요금
    Duration getRunningTime(); // 영화시간
    ReleaseStatus getReleaseStatus(); // 개봉상태
}
