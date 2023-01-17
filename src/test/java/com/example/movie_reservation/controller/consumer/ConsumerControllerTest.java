package com.example.movie_reservation.controller.consumer;

import com.example.movie_reservation.controller.consumer.dto.ConsumerRequestDto;
import com.example.movie_reservation.controller.consumer.dto.ConsumerResponseDto;
import com.example.movie_reservation.domain.consumer.Consumer;
import com.example.movie_reservation.fixture.CreateDto;
import com.example.movie_reservation.fixture.CreateEntity;
import com.example.movie_reservation.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ConsumerController.class)
class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumerService consumerService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("고객 정보 저장하기 테스트")
    void consumerAdd() throws Exception {
        // given
        ConsumerRequestDto consumerSaveDto = CreateDto.createConsumerSaveDto();
        given(consumerService.saveConsumer(any())).willReturn(1L);

        // when && then
        mockMvc.perform(post("/consumers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(consumerSaveDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));

        verify(consumerService).saveConsumer(any());
    }

    @Test
    @DisplayName("고객 정보 조회하기 테스트")
    void consumerFind() throws Exception {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        given(consumerService.findConsumer(any())).willReturn(consumer);

        ConsumerResponseDto dto = ConsumerResponseDto.of(consumer);

        // when && then
        mockMvc.perform(get("/consumers/" + consumer.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(dto)));

        verify(consumerService).findConsumer(any());
    }
}