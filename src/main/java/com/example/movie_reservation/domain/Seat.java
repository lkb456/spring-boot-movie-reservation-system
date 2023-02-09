package com.example.movie_reservation.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "SEATS")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEATS_ID")
    private Long id;

    @Column(name = "NUMBER")
    private int number;

    @ManyToOne
    private Theater theater;

    @Builder
    public Seat(int number, Theater theater) {
        this.number = number;
        this.theater = theater;
    }
}
