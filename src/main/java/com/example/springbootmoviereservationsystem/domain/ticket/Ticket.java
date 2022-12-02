package com.example.springbootmoviereservationsystem.domain.ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "serial_number")
    private UUID serialNumber; // 티켓 시리얼 번호

    private String movieTitle; // 영화 제목

    private int audienceCount; // 인원

    private LocalDateTime whenScreened; // 상영 시간

    private boolean isPublish; // 티켓 발행 상태

    private LocalDateTime publishTime; // 발행 시간

    public boolean isPublish() {
        return this.isPublish;
    }
}
