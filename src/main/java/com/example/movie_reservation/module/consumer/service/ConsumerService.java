package com.example.movie_reservation.module.consumer.service;

import com.example.movie_reservation.exception.NotFoundEntityException;
import com.example.movie_reservation.module.consumer.dto.ConsumerRequestDto;
import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.consumer.domain.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public Long saveConsumer(ConsumerRequestDto consumerRequestDto) {
        duplicatedNicknameOrPhoneNumberCheck(consumerRequestDto);
        Consumer savedConsumer = consumerRepository.save(consumerRequestDto.toEntity());
        return savedConsumer.getId();
    }

    private void duplicatedNicknameOrPhoneNumberCheck(ConsumerRequestDto consumerRequestDto) {
        if (isExists(consumerRequestDto)) {
            throw new IllegalArgumentException("중복된 고객 정보 입니다.");
        }
    }

    private boolean isExists(ConsumerRequestDto consumerRequestDto) {
        return consumerRepository.existsByNicknameOrPhoneNumber(
                consumerRequestDto.getNickname(),
                consumerRequestDto.getPhoneNumber()
        );
    }

    @Transactional
    public void updateConsumer(Long consumerId, ConsumerRequestDto consumerRequestDto) {
        Consumer consumer = findConsumer(consumerId);
        consumer.updateInfo(consumerRequestDto.getNickname(), consumerRequestDto.getPhoneNumber());
    }

    @Transactional
    public void leaveConsumer(Long consumerId) {
        Consumer consumer = findConsumer(consumerId);
        consumerRepository.delete(consumer);
    }

    public Consumer findConsumer(Long consumerId) {
        return consumerRepository.findById(consumerId)
                .orElseThrow(NotFoundEntityException::new);
    }
}
