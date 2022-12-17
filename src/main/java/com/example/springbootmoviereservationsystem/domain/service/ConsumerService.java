package com.example.springbootmoviereservationsystem.domain.service;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.consumer.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    public void updateConsumer(Long consumerId, ConsumerSaveAndUpdateRequestDto consumerSaveAndUpdateRequestDto) {
        Consumer consumer = findConsumer(consumerId);
        consumer.updateInfo(consumerSaveAndUpdateRequestDto.getNickname(), consumerSaveAndUpdateRequestDto.getPhoneNumber());
    }

    public Consumer findConsumer(Long consumerId) {
        return consumerRepository.findById(consumerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객정보입니다."));
    }
}
