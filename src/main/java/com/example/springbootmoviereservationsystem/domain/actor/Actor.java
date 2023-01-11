package com.example.springbootmoviereservationsystem.domain.actor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTORS")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 이름
    private LocalDateTime birthDate; // 생년월일
    private String image; // 사진
    private String content; // 배우 이력 내용


}
