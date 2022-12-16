package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.consumer.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.Consumer;
import com.example.springbootmoviereservationsystem.domain.repository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public Long saveConsumer(ConsumerSaveAndUpdateRequestDto consumerSaveRequestDto) {
        duplicatePhoneNumberCheck(consumerSaveRequestDto.getPhoneNumber());
        Consumer savedConsumer = consumerRepository.save(consumerSaveRequestDto.toEntity());
        return savedConsumer.getId();
    }

    private void duplicatePhoneNumberCheck(String phoneNumber) {
        if (isExists(phoneNumber)) {
            throw new IllegalArgumentException("중복된 전화번호 입니다.");
        }
    }

    private boolean isExists(String phoneNumber) {
        return consumerRepository.existsByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void updateConsumer(String phoneNumber, ConsumerSaveAndUpdateRequestDto consumerSaveAndUpdateRequestDto) {
        Consumer consumer = findConsumer(phoneNumber);
        consumer.update(consumerSaveAndUpdateRequestDto.getNickname(), consumerSaveAndUpdateRequestDto.getPhoneNumber());
    }

    public Consumer findConsumer(String phoneNumber) {
        return consumerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 핸드폰 번호입니다."));
    }
}
