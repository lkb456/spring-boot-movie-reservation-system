package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.request.ConsumerSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.request.ConsumerUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.consumer.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Consumer findConsumer(String phoneNumber) {
        return consumerFind(phoneNumber);
    }

    @Transactional
    public void updateConsumer(String phoneNumber, ConsumerUpdateRequestDto consumerUpdateRequestDto) {
        Consumer consumer = findConsumer(phoneNumber);
        consumer.update(consumerUpdateRequestDto.getNickname(), consumerUpdateRequestDto.getPhoneNumber());
    }

    private Consumer consumerFind(String phoneNumber) {
        return consumerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 핸드폰 번호입니다."));
    }
}
