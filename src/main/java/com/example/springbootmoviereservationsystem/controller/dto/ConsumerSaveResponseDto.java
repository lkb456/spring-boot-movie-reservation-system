package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConsumerSaveResponseDto {

    private final String nickname; // 고객 닉네임
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
