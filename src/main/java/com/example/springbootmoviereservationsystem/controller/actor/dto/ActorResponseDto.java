package com.example.springbootmoviereservationsystem.controller.actor.dto;

import com.example.springbootmoviereservationsystem.domain.actor.Actor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@RequiredArgsConstructor
public class ActorResponseDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String image;
    private final String content;

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
