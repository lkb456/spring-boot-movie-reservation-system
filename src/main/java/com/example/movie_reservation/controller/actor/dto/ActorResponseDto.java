package com.example.movie_reservation.controller.actor.dto;

import com.example.movie_reservation.domain.actor.Actor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ActorResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String image;
    private String content;

    @Builder
    public ActorResponseDto(Long id, String firstName, String lastName, LocalDate birthDate, String image, String content) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.image = image;
        this.content = content;
    }

    public static ActorResponseDto of(Actor actor) {
        return ActorResponseDto.builder()
                .id(actor.getId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .birthDate(actor.getBirthDate())
                .image(actor.getImage())
                .content(actor.getContent())
                .build();
    }
}
