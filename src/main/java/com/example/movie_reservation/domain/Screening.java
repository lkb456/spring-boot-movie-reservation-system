package com.example.movie_reservation.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "SCREENS")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCREENS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Movie movie;

    @Column(name = "FEE")
    private BigDecimal fee; // 영화 요금

    @ManyToMany
    private List<Theater> theaters = new ArrayList<>();

    @Column(name = "WHEN_SCREENED")
    private LocalDateTime whenScreened;

    @Builder
    public Screening(Movie movie, BigDecimal fee, LocalDateTime whenScreened) {
        this.movie = movie;
        this.fee = fee;
        this.whenScreened = whenScreened;
    }

    public void addTheater(Theater theater) {
        if (theater != null) {
            this.theaters.add(theater);
        }
    }

}
