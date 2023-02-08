package com.example.movie_reservation.service;

import com.example.movie_reservation.module.reservation.dto.PopularMovieResponseDto;
import com.example.movie_reservation.module.reservation.dto.ReservationResponseDto;
import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.example.movie_reservation.module.consumer.service.ConsumerService;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import com.example.movie_reservation.module.reservation.domain.Reservation;
import com.example.movie_reservation.module.reservation.domain.ReservationRepository;
import com.example.movie_reservation.module.reservation.domain.ReservationStatus;
import com.example.movie_reservation.module.reservation.service.ReservationService;
import com.example.movie_reservation.module.screening.domain.Screening;
import com.example.movie_reservation.module.screening.service.ScreeningService;
import com.example.movie_reservation.module.seat.domain.Seat;
import com.example.movie_reservation.module.seat.service.SeatService;
import com.example.movie_reservation.module.ticket.domain.Ticket;
import com.example.movie_reservation.module.ticket.domain.TicketRepository;
import com.example.movie_reservation.fixture.CreateDto;
import com.example.movie_reservation.fixture.CreateEntity;
import com.example.movie_reservation.module.screening.policy.DiscountPolicy;
import com.example.movie_reservation.infra.util.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Mock
    private DiscountPolicy discountPolicy;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("에매 정보 저장 하기 테스트")
    void reserveSave() {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Seat seat = CreateEntity.createSingleSeat(1);

        given(discountPolicy.calculateDiscountAmount(any())).willReturn(Money.ZERO);
        Reservation reservation = screening.reserve(consumer, 1, Money.ZERO);

        seat.reserve(reservation);

        given(consumerService.findConsumer(any())).willReturn(consumer);
        given(screeningService.findScreen(any())).willReturn(screening);
        given(reservationRepository.save(any())).willReturn(reservation);

        // when
        ReservationResponseDto result = reservationService.reserveSave(CreateDto.CreateReservationSaveRequestDto());

        // then
        assertThat(result.getAudienceCount()).isEqualTo(reservation.getAudienceCount());
        assertThat(result.getConsumerResponseDto().getNickname()).isEqualTo(consumer.getNickname());
        assertThat(result.getScreeningSaveResponseDto().getMovie().getTitle()).isEqualTo(movie.getTitle());
        assertThat(result.getSeats().size()).isEqualTo(reservation.getAudienceCount());

        verify(consumerService).findConsumer(any());
        verify(screeningService).findScreen(any());
        verify(reservationRepository).save(any());
    }

    @Test
    @DisplayName("예매 정보에 대한 티켓 발행하기 테스트")
    void ticketPublish() {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5, Money.ZERO);
        Ticket ticket = reservation.publishTicket();

        given(reservationRepository.findById(any())).willReturn(Optional.of(reservation));
        given(ticketRepository.save(any())).willReturn(ticket);

        // when
        reservationService.ticketPublish(1L);

        // then
        assertThat(ticket).isEqualTo(consumer.getTicket());
        assertThat(ticket.isPublish()).isTrue();

        verify(reservationRepository, atLeastOnce()).findById(any());
        verify(ticketRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("예매 취소하기 테스트")
    void cancelReservation() {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5, Money.ZERO);

        given(reservationRepository.findById(any())).willReturn(Optional.of(reservation));

        // when
        reservationService.cancelReservation(1L);

        // then
        assertThat(reservation.getReservationStatus()).isEqualTo(ReservationStatus.CANCELLED_BY_CUSTOMER);

        verify(reservationRepository).findById(any());
    }

    @Test
    @DisplayName("예매 취소하기 예외 테스트 (티켓이 이미 발행 된 경우")
    void cancelReservation_Exception() {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reservation = screening.reserve(consumer, 5, Money.ZERO);
        Ticket ticket = reservation.publishTicket();

        given(ticketRepository.save(any())).willReturn(ticket);
        given(reservationRepository.findById(any())).willReturn(Optional.of(reservation));

        reservationService.ticketPublish(1L);

        // when && then
        assertThatThrownBy(() -> reservationService.cancelReservation(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓 발행 이후 취소는 안됩니다!");

        verify(ticketRepository).save(any());
        verify(reservationRepository, atMost(2)).findById(any());
    }

    @Test
    @DisplayName("예매 수가 가장 높은 상영 영화 정보 가져오기")
    void ReservationServiceTest() {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reserve = screening.reserve(consumer, 5, Money.ZERO);

        Long count = 10L;
        given(reservationRepository.findBestMovie(any())).willReturn(List.of(PopularMovieResponseDto.builder()
                .movie(reserve.getScreening().getMovie())
                .reserveCount(count)
                .build()));

        // when
        List<PopularMovieResponseDto> result = reservationService.bestMovieFind();

        // then
        assertThat(screening.getMovie().getTitle()).isEqualTo(result.get(0).getMovie().getTitle());
        assertThat(count).isEqualTo(result.get(0).getReserveCount());

        verify(reservationRepository).findBestMovie(any());
    }

}