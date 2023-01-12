package com.example.movie_reservation.service.actor;

import com.example.movie_reservation.controller.actor.dto.ActorRequestDto;
import com.example.movie_reservation.controller.actor.dto.ActorResponseDto;
import com.example.movie_reservation.domain.actor.Actor;
import com.example.movie_reservation.domain.actor.ActorRepository;
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
