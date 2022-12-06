package com.example.springbootmoviereservationsystem.domain.consumer;

import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
<<<<<<< HEAD
import jakarta.persistence.*;
=======
>>>>>>> feature/test
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

<<<<<<< HEAD
=======
import javax.persistence.*;
>>>>>>> feature/test
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONSUMERS", indexes = @Index(columnList = "PHONE_NUMBER"))
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONSUMERS_ID")
    private Long id; // pk값

    @Column(name = "NICKNAME", unique = true)
    private String nickname; // 닉네임

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber; // 휴대전화 번호

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SERIAL_NUMBER")
    private Ticket ticket; // 예매 티켓

    @CreationTimestamp
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt; // 생성 시간

    @UpdateTimestamp
    private LocalDateTime updateAt; // 수정 시간

    public void receive(Ticket ticket) {
        if (ticket.isPublish()) {
            this.ticket = ticket;
            return;
        }

        throw new IllegalArgumentException("티켓 발행이 되지 않았습니다.");
    }

    public void update(String nickname, String phoneNumber) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
