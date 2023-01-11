package com.example.springbootmoviereservationsystem.domain.actor;

import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "ACTORS")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTORS_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName; // 성

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName; // 이름

    @Column(name = "BIRTH_DATA")
    private LocalDate birthDate; // 생년월일

    @Column
    private String image; // 사진

    @Column
    @Lob
    private String content; // 배우 이력 내용

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createAt; // 생성 시간

    @UpdateTimestamp
    private LocalDateTime updateAt; // 수정 시간

    @Builder
    public Actor(String firstName, String lastName, LocalDate birthDate, String image, String content) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.image = image;
        this.content = content;
    }

    public void addMovie(Set<Movie> movies) {
        this.movies.addAll(movies);
    }
}
