package com.example.springbootmoviereservationsystem.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "CONSUMERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONSUMERS_ID")
    private Long id; // pk값

    @Column(name = "NICKNAME", unique = true)
    private String nickname; // 닉네임

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber; // 휴대전화 번호

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SERIAL_NUMBER")
    private Ticket ticket; // 예매 티켓

    @CreationTimestamp
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt; // 생성 시간

    @UpdateTimestamp
    private LocalDateTime updateAt; // 수정 시간

    @Builder
    public Consumer(Long id, String nickname, String phoneNumber) {
        this.id = id;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public void receiveTicket(Ticket ticket) {
        if (ticket.isPublish()) {
            this.ticket = ticket;
            return;
        }

        throw new IllegalArgumentException("티켓 발행이 되지 않았습니다.");
    }

    public void updateInfo(String nickname, String phoneNumber) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public boolean hasTicket() {
        return ticket.isPublish();
    }

    public void cancelTicket() {
        this.ticket.cancel();
        this.ticket = null;
    }
}
