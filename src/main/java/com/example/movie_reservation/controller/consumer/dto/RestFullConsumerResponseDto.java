package com.example.movie_reservation.controller.consumer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;

@Getter
@Builder
@RequiredArgsConstructor
public class RestFullConsumerResponseDto {

    private final ConsumerResponseDto consumer;
    private final Link link;

}
