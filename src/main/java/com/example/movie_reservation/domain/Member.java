package com.example.movie_reservation.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_ID")
    private Long id; // pk값

    @Column(name = "NICKNAME", unique = true)
    private String nickname; // 닉네임

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber; // 휴대전화 번호

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TICKET")
    private Ticket ticket; // 예매 티켓

    @CreationTimestamp
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt; // 생성 시간

    @UpdateTimestamp
    @Column(name = "UPDATE_AT")
    private LocalDateTime updateAt; // 수정 시간

    @Builder
    public Member(String nickname, String phoneNumber) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
