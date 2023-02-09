package com.example.movie_reservation.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "THEATERS")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "NUMBER_OF_SEAT")
    private int numberOfSeat;

    @Builder
    public Theater(String name, int numberOfSeat) {
        this.name = name;
        this.numberOfSeat = numberOfSeat;
    }
}