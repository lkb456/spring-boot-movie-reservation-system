package com.example.movie_reservation.module.actor.service;

import com.example.movie_reservation.module.actor.domain.ActorRepository;
import com.example.movie_reservation.module.actor.controller.dto.ActorRequestDto;
import com.example.movie_reservation.module.actor.controller.dto.ActorResponseDto;
import com.example.movie_reservation.module.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorResponseDto saveActor(ActorRequestDto actorRequestDto) {
        Actor savedActor = actorRepository.save(actorRequestDto.toEntity());
        return ActorResponseDto.of(savedActor);
    }

    public Actor findActor(Long actorId) {
        return actorRepository.findById(actorId)
                .orElseThrow(() -> new IllegalArgumentException("Actor Entity is Not Found !!"));
    }
}
