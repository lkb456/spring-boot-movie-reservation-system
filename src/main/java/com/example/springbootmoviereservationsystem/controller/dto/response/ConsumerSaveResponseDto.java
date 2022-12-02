package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConsumerSaveResponseDto {

    private final String nickname; // 고객 닉네임

    @JsonProperty("phone")
    private final String phoneNumber; // 고객 핸드폰 번호

    private final Ticket ticket; // 티켓

    @Builder
    public ConsumerSaveResponseDto(String nickname, String phoneNumber, Ticket ticket) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.ticket = ticket;
    }

    public static ConsumerSaveResponseDto of(Consumer consumer) {
         return ConsumerSaveResponseDto.builder()
                 .nickname(consumer.getNickname())
                 .phoneNumber(consumer.getPhoneNumber())
                 .ticket(consumer.getTicket())
                 .build();
    }
}
