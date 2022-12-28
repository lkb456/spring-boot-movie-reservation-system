package com.example.springbootmoviereservationsystem.service;

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

    public Long saveConsumer(ConsumerSaveAndUpdateRequestDto consumerRequestDto) {
        duplicatedNicknameOrPhoneNumberCheck(consumerRequestDto);
        Consumer savedConsumer = consumerRepository.save(consumerRequestDto.toEntity());
        return savedConsumer.getId();
    }

    private void duplicatedNicknameOrPhoneNumberCheck(ConsumerSaveAndUpdateRequestDto consumerRequestDto) {
        if (isExists(consumerRequestDto)) {
            throw new IllegalArgumentException("중복된 고객 정보 입니다.");
        }
    }

    private boolean isExists(ConsumerSaveAndUpdateRequestDto consumerRequestDto) {
        return consumerRepository.existsByNicknameOrPhoneNumber(
                consumerRequestDto.getNickname(),
                consumerRequestDto.getPhoneNumber()
        );
    }

    @Transactional
    public void updateConsumer(Long consumerId, ConsumerSaveAndUpdateRequestDto consumerRequestDto) {
        Consumer consumer = findConsumer(consumerId);
        consumer.updateInfo(consumerRequestDto.getNickname(), consumerRequestDto.getPhoneNumber());
    }

    @Transactional
    public void leaveConsumer(Long consumerId) {
        Consumer consumer = findConsumer(consumerId);
        consumerRepository.delete(consumer);;
    }

    public Consumer findConsumer(Long consumerId) {
        return consumerRepository.findById(consumerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객정보입니다."));
    }
}
