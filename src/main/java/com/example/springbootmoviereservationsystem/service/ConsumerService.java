package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.ConsumerSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.ConsumerSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.consumer.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public Long saveConsumer(ConsumerSaveRequestDto consumerSaveRequestDto) {
        duplicateCheck(consumerSaveRequestDto);
        Consumer savedConsumer = consumerRepository.save(consumerSaveRequestDto.toEntity());
        return savedConsumer.getId();
    }

    private void duplicateCheck(ConsumerSaveRequestDto consumerSaveRequestDto) {
        if (isExists(consumerSaveRequestDto.getPhoneNumber())) {
            throw new IllegalArgumentException("중복된 전화번호 입니다.");
        }
    }

    private boolean isExists(String phoneNumber) {
        return consumerRepository.existsByPhoneNumber(phoneNumber);
    }

    public ConsumerSaveResponseDto findConsumer(String phoneNumber) {
        return ConsumerSaveResponseDto.of(find(phoneNumber));
    }

    private Consumer find(String phoneNumber) {
        return consumerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 핸드폰 번호입니다."));
    }
}
