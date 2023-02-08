package com.example.movie_reservation.module.consumer;

import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.ticket.domain.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.movie_reservation.fixture.CreateEntity.createConsumer;
import static com.example.movie_reservation.fixture.CreateEntity.createPublishTicket;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsumerTest {

    @Test
    @DisplayName("정보 업데이트")
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
        Ticket ticket = createPublishTicket();

        // when
        consumer.receiveTicket(ticket);

        // then
        assertThat(consumer.hasTicket()).isTrue();

        consumer.cancelTicket();
        assertThat(consumer.getTicket().isPublish()).isFalse();
    }
}