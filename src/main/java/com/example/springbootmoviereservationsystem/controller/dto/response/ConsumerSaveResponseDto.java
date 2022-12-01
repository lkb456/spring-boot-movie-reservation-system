package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConsumerSaveResponseDto {

    private final String nickname; // 고객 닉네임

    @JsonProperty("phone")
    private final String phoneNumber; // 고객 핸드폰 번호

    @Builder
    public ConsumerSaveResponseDto(String nickName, String phoneNumber) {
        this.nickname = nickName;
        this.phoneNumber = phoneNumber;
    }

    public static ConsumerSaveResponseDto of(Consumer consumer) {
         return ConsumerSaveResponseDto.builder()
                 .nickName(consumer.getNickname())
                 .phoneNumber(consumer.getPhoneNumber())
                 .build();
    }
}
