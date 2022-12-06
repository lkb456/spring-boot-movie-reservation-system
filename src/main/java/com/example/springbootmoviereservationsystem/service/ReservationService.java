package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.request.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ReservationSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.ticket.Ticket;
import com.example.springbootmoviereservationsystem.domain.ticket.TicketRepository;
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
    private final TicketRepository ticketRepository;

    @Transactional
    public ReservationSaveResponseDto reserveSave(ReservationSaveRequestDto reservationSaveRequestDto) {
        Consumer consumer = consumerService.findConsumer(reservationSaveRequestDto.getPhoneNumber());
        Screening screening = screeningService.findScreen(reservationSaveRequestDto.getScreeningId());
        Reservation savedReservation = reservationRepository
                .save(screening.reserve(consumer, reservationSaveRequestDto.getAudienceCount()));
        return ReservationSaveResponseDto.of(savedReservation);
    }

    @Transactional
    public void ticketPublish(Long reservationId) {
        Reservation reservation = reservationFind(reservationId);
        Ticket savedTicket = ticketRepository.save(reservation.publishTicket());
        reservation.getConsumer().receive(savedTicket);
    }

    private Reservation reservationFind(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예매정보입니다."));
    }
}