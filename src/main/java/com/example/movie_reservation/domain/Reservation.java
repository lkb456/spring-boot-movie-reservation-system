package com.example.movie_reservation.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATIONS_ID")
    private Long id;

    @Column(name = "NICKNAME")
    private String nickname; // 예매자

    @Column(name = "AUDIENCE_COUNT")
    private int audienceCount; // 고객 인원 수

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SCREENS_ID")
    private Screening screening; // 상영 정보

    @Column(name = "FEE")
    private BigDecimal fee; // 예매 요금

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예매 상태

    @CreationTimestamp
    @Column(name = "RESERVE_TIME")
    private LocalDateTime reserveTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updatedTime;

    @Builder
    public Reservation(String nickname, int audienceCount, Screening screening, BigDecimal fee, ReservationStatus reservationStatus) {
        this.nickname = nickname;
        this.audienceCount = audienceCount;
        this.screening = screening;
        this.fee = fee;
        this.reservationStatus = reservationStatus;
    }
}