package com.example.movie_reservation.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ACTORS")
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTORS_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name; // 이름

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate; // 생년월일

    @Column(name = "IMAGE_URL", unique = true)
    private String imageUrl; // 사진 경로

    @Column(name = "CONTENT")
    @Lob
    private String content; // 배우 이력 내용

    @CreationTimestamp
    private LocalDateTime createAt; // 생성 시간

    @UpdateTimestamp
    private LocalDateTime updateAt; // 수정 시간

    @Builder
    public Actor(String name, LocalDate birthDate, String imageUrl, String content) {
        this.name = name;
        this.birthDate = birthDate;
        this.imageUrl = imageUrl;
        this.content = content;
    }
}
