package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.reservation.dto.ReservationResponseDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.seat.Seat;
import com.example.springbootmoviereservationsystem.domain.ticket.TicketRepository;
import com.example.springbootmoviereservationsystem.fixture.CreateDto;
import com.example.springbootmoviereservationsystem.fixture.CreateEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private SeatService seatService;

    @Mock
    private ConsumerService consumerService;

    @Mock
    private ScreeningService screeningService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("에매 정보 저장 하기 테스트")
    void reserveSave() {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5);

        given(consumerService.findConsumer(any())).willReturn(consumer);
        given(screeningService.findScreen(any())).willReturn(screening);

        for (int count = 0; count <= reservation.getAudienceCount(); count++) {
            Seat seat = CreateEntity.createSingleSeat();
            seat.reserve(reservation);
            given(seatService.findSeat(any())).willReturn(seat);
        }

        given(reservationRepository.save(any())).willReturn(reservation);

        // when
        ReservationResponseDto result = reservationService.reserveSave(CreateDto.CreateReservationSaveRequestDto());

        // then
        assertThat(result.getAudienceCount()).isEqualTo(reservation.getAudienceCount());
        assertThat(result.getConsumerSaveResponseDto().getNickname()).isEqualTo(consumer.getNickname());
        assertThat(result.getScreeningSaveResponseDto().getMovieDto().getTitle()).isEqualTo(movie.getTitle());
        assertThat(result.getSeats().size()).isEqualTo(reservation.getAudienceCount());

        verify(consumerService, atLeastOnce()).findConsumer(any());
        verify(screeningService, atLeastOnce()).findScreen(any());
        verify(seatService, times(reservation.getAudienceCount())).findSeat(any());
        verify(reservationRepository, atLeastOnce()).save(any());
    }
}