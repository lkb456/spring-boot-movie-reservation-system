package com.example.movie_reservation.controller.actor;

import com.example.movie_reservation.module.actor.controller.dto.ActorRequestDto;
import com.example.movie_reservation.module.actor.controller.dto.ActorResponseDto;
import com.example.movie_reservation.module.actor.domain.ActorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ActorRepository actorRepository;

    @AfterEach
    public void clear() {
        actorRepository.deleteAll();
    }

    @Test
    @DisplayName("배우 정보 등록하기 테스트")
    void createActor() {
        // given
        ActorRequestDto actorRequestDto = ActorRequestDto.builder()
                .firstName("마")
                .lastName("동석")
                .birthDate(LocalDate.now())
                .image("이미지")
                .content("내용")
                .build();
        String url = "http://localhost:" + port + "/actors";

        // when
        ResponseEntity<ActorResponseDto> result = restTemplate.postForEntity(url, actorRequestDto, ActorResponseDto.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNotNull();
    }
}