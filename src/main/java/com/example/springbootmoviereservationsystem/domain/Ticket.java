package com.example.springbootmoviereservationsystem.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "SERIAL_NUMBER")
    private UUID serialNumber; // 티켓 시리얼 번호

    @Column(name = "MOVIE_TITLE")
    private String movieTitle; // 영화 제목

    @Column(name = "AUDIENCE_COUNT")
    private int audienceCount; // 인원

    @Column(name = "WHEN_SCREENED")
    private LocalDateTime whenScreened; // 상영 시간

    @Column(name = "IS_PUBLISH")
    private boolean isPublish = false; // 티켓 발행 상태

    @CreationTimestamp
    @Column(name = "PUBLISH_TIME")
    private LocalDateTime publishTime; // 발행 시간

    public boolean isPublish() {
        return this.isPublish;
    }
}
