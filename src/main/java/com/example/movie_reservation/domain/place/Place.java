package com.example.movie_reservation.domain.place;

import com.example.movie_reservation.domain.seat.Seat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "PLACES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACES_ID")
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLACES_ID")
    private Set<Seat> seats = new HashSet<>();

    @Builder
    public Place(String name) {
        this.name = name;
    }

    public void addSeat(Set<Seat> sets) {
        this.seats.addAll(sets);
    }
}
