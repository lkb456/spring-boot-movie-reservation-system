package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIES_ID")
    private Long id; // 순번

    @Column(name = "TITLE")
    private String title; // 제목

    @Column(name = "FEE")
    private Long fee; // 요금

    @Column(name = "RUNNING_TIME")
    private Duration runningTime; // 상영 시간

    @Enumerated(value = EnumType.STRING)
    @Column(name = "RELEASE_STATUS")
    private ReleaseStatus releaseStatus; // 개봉 상태

    @CreationTimestamp
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt; // 생성 시간

    @Builder
    public Movie(String title, Long fee, Duration runningTime, ReleaseStatus releaseStatus) {
        this.title = title;
        this.fee = fee;
        this.runningTime = runningTime;
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

    public void updateInfo(String title, Long fee, Duration runningTime, ReleaseStatus releaseStatus) {
        this.title = title;
        this.fee = fee;
        this.runningTime = runningTime;
        this.releaseStatus = releaseStatus;
    }
}
