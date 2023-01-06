package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.reservation.dto.ReservationResponseDto;
import com.example.springbootmoviereservationsystem.controller.reservation.dto.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.seat.dto.SeatRequestDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.money.Money;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationStatus;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.seat.Seat;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.domain.ticket.TicketRepository;
import com.example.springbootmoviereservationsystem.infra.policy.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final SeatService seatService;
    private final ConsumerService consumerService;
    private final ScreeningService screeningService;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
    private final DiscountPolicy discountPolicy;

    @Transactional
    public ReservationResponseDto reserveSave(ReservationSaveRequestDto reservationSaveRequestDto) {
        Reservation reservation = createReservation(reservationSaveRequestDto);
        createSeatByAudienceCount(reservationSaveRequestDto, reservation);
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationResponseDto.of(savedReservation);
    }

    private Reservation createReservation(ReservationSaveRequestDto reservationSaveRequestDto) {
        Consumer consumer = consumerService.findConsumer(reservationSaveRequestDto.getConsumerId());
        Screening screening = screeningService.findScreen(reservationSaveRequestDto.getScreeningId());
        Money discountAmount = discountPolicy.calculateDiscountAmount(screening);
        return screening.reserve(consumer, reservationSaveRequestDto.getAudienceCount(), discountAmount);
    }

    private void createSeatByAudienceCount(ReservationSaveRequestDto reservationSaveRequestDto, Reservation reservation) {
        for (int count = 0; count < reservationSaveRequestDto.getAudienceCount(); count++) {
            List<SeatRequestDto> seats = reservationSaveRequestDto.getSeatSaveRequestDto();
            SeatRequestDto seatRequestDto = seats.get(count);
            updateSeat(reservation, seatRequestDto.getSeatId());
        }
    }

    private void updateSeat(Reservation reservation, Long seatId) {
        Seat seat = seatService.findSeat(seatId);
        if (seat.isAvailable()) {
            throw new IllegalArgumentException("이미 예약한 좌석입니다.");
        }

        seat.reserve(reservation);
        seat.updateReserveStatus(ReservationStatus.RESERVATION);
    }

    @Transactional
    public void ticketPublish(Long reservationId) {
        Reservation reservation = findReservation(reservationId);
        Ticket savedTicket = ticketRepository.save(reservation.publishTicket());
        reservation.getConsumer().receiveTicket(savedTicket);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = findReservation(reservationId);
        reservation.cancel();
    }

    public Reservation findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예매정보입니다."));
    }
}