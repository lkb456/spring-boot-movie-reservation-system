package com.example.springbootmoviereservationsystem.domain.movie;

import com.example.springbootmoviereservationsystem.domain.money.Money;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Money fee; // 요금 (수정)

    @Column(name = "RUNNING_TIME")
    private Duration runningTime; // 상영 시간

    @Enumerated(value = EnumType.STRING)
    @Column(name = "RELEASE_STATUS")
    private ReleaseStatus releaseStatus; // 개봉 상태

    @CreationTimestamp
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt; // 생성 시간

    @Builder
    public Movie(Long id, String title, Money amount, Duration runningTime, ReleaseStatus releaseStatus) {
        this.id = id;
        this.title = title;
        this.fee = amount;
        this.runningTime = runningTime;
        this.releaseStatus = releaseStatus;
    }

    public boolean isReleaseMovie() {
        return this.releaseStatus.isRelease(ReleaseStatus.RELEASE);
    }

    public Money calculateFee(int audienceCount) {
        return this.fee.times(audienceCount);
    }

    public void updateInfo(String title, Money amount, Duration runningTime, ReleaseStatus releaseStatus) {
        this.title = title;
        this.fee = amount;
        this.runningTime = runningTime;
        this.releaseStatus = releaseStatus;
    }
}
