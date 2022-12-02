package com.example.springbootmoviereservationsystem.domain.consumer;

import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumers_id")
    private Long id; // pk값
    private String nickname; // 닉네임

    @Column(unique = true)
    private String phoneNumber; // 휴대전화 번호

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "serial_number")
    private Ticket ticket; // 예매 티켓

    @CreationTimestamp
    private LocalDateTime createAt; // 생성 시간

    public void receive(Ticket ticket) {
        if (ticket.isPublish()) {
            this.ticket = ticket;
            return;
        }

        throw new IllegalArgumentException("티켓 발행이 되지 않았습니다.");
    }
}
