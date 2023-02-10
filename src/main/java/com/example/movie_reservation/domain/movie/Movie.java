package com.example.movie_reservation.domain.movie;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MOVIES", indexes = @Index(columnList = "RELEASE_DATE"))
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIES_ID")
    private Long id; // pk

    @Column(name = "TITLE", nullable = false)
    private String title; // 제목

    @Column(name = "RUNNING_TIME", nullable = false)
    private Duration runningTime; // 상영 시간

    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDate releaseDate; // 개봉일

    @CreationTimestamp
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime; // 생성 시간

    @UpdateTimestamp
    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime; // 수정 시간

    @Builder
    public Movie(String title, Duration runningTime, LocalDate releaseDate) {
        this.title = title;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
    }
}
