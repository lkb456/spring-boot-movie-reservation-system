package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ConsumerSaveResponseDto {

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
