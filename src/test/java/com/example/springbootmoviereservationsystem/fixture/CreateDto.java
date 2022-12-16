package com.example.springbootmoviereservationsystem.fixture;

import com.example.springbootmoviereservationsystem.controller.dto.consumer.ConsumerSaveAndUpdateRequestDto;

public class CreateDto {

    public static ConsumerSaveAndUpdateRequestDto createConsumerSaveDto(String nickname, String phoneNumber) {
        return ConsumerSaveAndUpdateRequestDto.builder()
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }
}
