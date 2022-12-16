package com.example.springbootmoviereservationsystem.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsumerTest {

    @Test
    @DisplayName("고객 정보 수정히기 테스트")
    void updateInfo() {
        // given
        String expectedNickname = "대림동 물주먹";
        String expectedPhoneNumber = "01011111234";

        Consumer consumer = createConsumer();

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
        Consumer consumer = createConsumer();

        Ticket ticket = Ticket.builder()
                .movieTitle("싸움의 기술")
                .audienceCount(2)
                .whenScreened(LocalDateTime.of(2022, 12, 17, 13, 00))
                .isPublish(true)
                .build();

        // when
        consumer.receiveTicket(ticket);

        // then
        assertThat(consumer.hasTicket()).isTrue();

        consumer.cancelTicket();
        assertThat(consumer.getTicket()).isNull();
    }

    private Consumer createConsumer() {
        return Consumer.builder()
                .nickname("대림동 불주먹")
                .phoneNumber("01012341234")
                .build();
    }
}