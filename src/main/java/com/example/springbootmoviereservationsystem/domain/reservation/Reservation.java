package com.example.springbootmoviereservationsystem.domain.reservation;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consumers_id")
    private Consumer consumer; // 고객 정보
    private int audienceCount; // 고객 인원 수

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Screening screening; // 상영 정보
    private Long fee; // 예매 요금

    public Ticket publishTicket() {
        return Ticket.builder()
                .movieTitle(screening.getMovie().getTitle())
                .audienceCount(audienceCount)
                .isPublish(true)
                .whenScreened(screening.getWhenScreened())
                .build();
    }
}