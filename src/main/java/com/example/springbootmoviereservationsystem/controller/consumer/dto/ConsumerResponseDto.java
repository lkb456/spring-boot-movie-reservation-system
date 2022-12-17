package com.example.springbootmoviereservationsystem.controller.consumer.dto;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class ConsumerResponseDto {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ConsumerDetailResponseDto {

        @JsonProperty("consumer_id")
        private final Long id; // pk값

        private final String nickname; // 닉네임

        @JsonProperty("phone")
        private final String phoneNumber; // 휴대전화 번호

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private final Ticket ticket; // 예매 티켓

        @JsonProperty("create_at")
        private final LocalDateTime createAt; // 생성 시간

        public static ConsumerDetailResponseDto of(Consumer consumer) {
            return ConsumerDetailResponseDto.builder()
                    .id(consumer.getId())
                    .nickname(consumer.getNickname())
                    .phoneNumber(consumer.getPhoneNumber())
                    .ticket(consumer.getTicket())
                    .createAt(consumer.getCreateAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ConsumerSaveResponseDto {

        private final String nickname; // 고객 닉네임

        @JsonProperty("phone")
        private final String phoneNumber; // 고객 핸드폰 번호

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private final Ticket ticket; // 티켓

        public static ConsumerSaveResponseDto of(Consumer consumer) {
            return ConsumerSaveResponseDto.builder()
                    .nickname(consumer.getNickname())
                    .phoneNumber(consumer.getPhoneNumber())
                    .ticket(consumer.getTicket())
                    .build();
        }
    }
}
