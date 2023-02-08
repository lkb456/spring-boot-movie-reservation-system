package com.example.movie_reservation.service;

import com.example.movie_reservation.module.consumer.dto.ConsumerRequestDto;
import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.consumer.domain.ConsumerRepository;
import com.example.movie_reservation.fixture.CreateDto;
import com.example.movie_reservation.module.consumer.service.ConsumerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.movie_reservation.fixture.CreateEntity.createConsumer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
class ConsumerServiceTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @InjectMocks
    private ConsumerService consumerService;

    @Test
    @DisplayName("고객 정보 저장 및 조회")
    void saveConsumer() {
        Consumer consumer = createConsumer();
        given(consumerRepository.save(any())).willReturn(consumer);

        ConsumerRequestDto dto = CreateDto.createConsumerSaveDto();
        Long savedId = consumerService.saveConsumer(dto);
        given(consumerRepository.findById(savedId)).willReturn(Optional.of(consumer));

        assertThat(savedId).isEqualTo(consumer.getId());

        Consumer findConsumer = consumerService.findConsumer(savedId);
        assertThat(findConsumer.getId()).isEqualTo(consumer.getId());

        verify(consumerRepository).save(any());
        verify(consumerRepository).findById(consumer.getId());
    }

    @Test
    @DisplayName("중복된 정보 예외 테스트")
    void duplicateExceptionTest() {
        given(consumerRepository.existsByNicknameOrPhoneNumber(any(), any())).willReturn(true);

        ConsumerRequestDto dto = CreateDto.createConsumerSaveDto();
        assertThatThrownBy(() -> consumerService.saveConsumer(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 고객 정보 입니다.");

        verify(consumerRepository).existsByNicknameOrPhoneNumber(any(), any());
    }

    @Test
    @DisplayName("고객정보 업데이트")
    void updateConsumer() {
        Consumer consumer = createConsumer();
        given(consumerRepository.findById(any())).willReturn(Optional.of(consumer));

        Long consumerId = 1L;
        ConsumerRequestDto dto = CreateDto.createConsumerSaveDto();
        consumerService.updateConsumer(consumerId, dto);

        assertThat(dto.getNickname()).isEqualTo(consumer.getNickname());
        assertThat(dto.getPhoneNumber()).isEqualTo(consumer.getPhoneNumber());

        verify(consumerRepository).findById(consumerId);
    }

    @Test
    @DisplayName("고객 정보가 없는 경우 예외 테스트")
    void findConsumer_Exception() {
        given(consumerRepository.findById(any()))
                .willThrow(new IllegalArgumentException("존재하지 않는 고객정보입니다."));

        assertThatThrownBy(() -> consumerService.findConsumer(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 고객정보입니다.");

        verify(consumerRepository).findById(1L);
    }
}