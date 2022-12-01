package com.example.springbootmoviereservationsystem.domain.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 순번

    private String title; // 제목
    private Long fee; // 요금
    private Duration runningTime; // 상영 시간

    @Enumerated(value = EnumType.STRING)
    private ReleaseStatus releaseStatus; // 개봉 상태

    @CreationTimestamp
    private LocalDateTime createAt;

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
