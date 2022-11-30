package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConsumerSaveResponseDto {

    private final String nickName;
    private final String phoneNumber;

    @Builder
    public ConsumerSaveResponseDto(String nickName, String phoneNumber) {
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public static ConsumerSaveResponseDto of(Consumer consumer) {
         return ConsumerSaveResponseDto.builder()
                 .nickName(consumer.getNickName())
                 .phoneNumber(consumer.getPhoneNumber())
                 .build();
    }
}
