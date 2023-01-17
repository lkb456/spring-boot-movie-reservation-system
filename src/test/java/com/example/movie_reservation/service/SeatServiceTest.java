package com.example.movie_reservation.service;

import com.example.movie_reservation.domain.seat.Seat;
import com.example.movie_reservation.domain.seat.SeatRepository;
import com.example.movie_reservation.fixture.CreateEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
class SeatServiceTest {

    private ClassPathResource resource = new ClassPathResource("static/seat/position.txt");

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    @Test
    @DisplayName("좌석 정보 조회하기 테스트")
    void findSeat() {
        // given
        Seat seat = CreateEntity.createSingleSeat(1);
        given(seatRepository.findBySeatNumber(any())).willReturn(Optional.of(seat));

        // when
        Seat findSeat = seatService.findSeat(seat.getSeatNumber());

        // then
        assertThat(seat).isEqualTo(findSeat);

        verify(seatRepository, atLeast(1)).findBySeatNumber(any());
    }
}