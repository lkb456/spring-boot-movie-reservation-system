package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.reservation.ReservationResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.reservation.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.seat.SeatDto;
import com.example.springbootmoviereservationsystem.domain.*;
import com.example.springbootmoviereservationsystem.domain.repository.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.repository.TicketRepository;
import com.example.springbootmoviereservationsystem.domain.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConsumerService consumerService;
    private final ScreeningService screeningService;
    private final SeatService seatService;
    private final TicketRepository ticketRepository;


    @Transactional
    public ReservationResponseDto reserveSave(ReservationSaveRequestDto reservationSaveRequestDto) {
        Consumer consumer = consumerService.findConsumer(reservationSaveRequestDto.getPhoneNumber());
        Screening screening = screeningService.findScreen(reservationSaveRequestDto.getScreeningId());

        Reservation reservation = screening.reserve(consumer, reservationSaveRequestDto.getAudienceCount());
        createSeatByAudienceCount(reservationSaveRequestDto, reservation);

        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationResponseDto.of(savedReservation);
    }

    private void createSeatByAudienceCount(ReservationSaveRequestDto reservationSaveRequestDto, Reservation reservation) {
        for (int count = 0; count < reservationSaveRequestDto.getAudienceCount(); count++) {
            SeatDto.SaveRequestDto saveRequestDto = reservationSaveRequestDto.getSeatSaveRequestDto().get(count);
            Seat seat = seatService.findSeat(saveRequestDto.getSeatId());
            seat.updateReservationStatus(ReservationStatus.RESERVATION);
            seat.reserve(reservation);
        }
    }

    @Transactional
    public void ticketPublish(Long reservationId) {
        Reservation reservation = reservationFind(reservationId);
        Ticket savedTicket = ticketRepository.save(reservation.publishTicket());
        reservation.getConsumer().receive(savedTicket);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationFind(reservationId);
        reservation.cancel();
    }

    public Reservation reservationFind(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예매정보입니다."));
    }
}