package com.example.springbootmoviereservationsystem.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.example.springbootmoviereservationsystem.domain.CreateEntity.createConsumer;
import static com.example.springbootmoviereservationsystem.domain.CreateEntity.createTicket;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsumerTest {

    @Test
    @DisplayName("정보 업데이트")
    void updateInfo() {
        // given
        String expectedNickname = "대림동 물주먹";
        String expectedPhoneNumber = "01011111234";

        Consumer consumer = createConsumer(1L, "대림동 불주먹", "01012341234");

        // when
        consumer.updateInfo(expectedNickname, expectedPhoneNumber);

        // then
        assertEquals(expectedNickname, consumer.getNickname());
        assertEquals(expectedPhoneNumber, consumer.getPhoneNumber());
    }

    @Test
    @DisplayName("티켓 발행 및 취소 테스트")
    void receiveTicket() {
        // given
        Consumer consumer = createConsumer(1L, "대림동 불주먹", "01012341234");
        Ticket ticket = createTicket("싸움의 기술", 2, LocalDateTime.of(2022, 12, 17, 13, 00), true);

        // when
        consumer.receiveTicket(ticket);

        // then
        assertThat(consumer.hasTicket()).isTrue();

        consumer.cancelTicket();
        assertThat(consumer.getTicket()).isNull();
    }
}