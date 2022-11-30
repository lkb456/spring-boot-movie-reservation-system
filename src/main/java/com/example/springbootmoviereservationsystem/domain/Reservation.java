package com.example.springbootmoviereservationsystem.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Consumer consumer; // 고객 정보
    private int audienceCount; // 고객 인원 수

    @ManyToOne
    private Screening screening; // 상영 정보
    private Long fee; // 예매 요금

    @Builder
    public Reservation(Consumer consumer, int audienceCount, Screening screening, Long fee) {
        this.consumer = consumer;
        this.audienceCount = audienceCount;
        this.screening = screening;
        this.fee = fee;
    }

}