package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.reservation.ReservationResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.reservation.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.seat.SeatDto;
import com.example.springbootmoviereservationsystem.domain.*;
import com.example.springbootmoviereservationsystem.domain.repository.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.repository.TicketRepository;
import com.example.springbootmoviereservationsystem.domain.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final SeatService seatService;
    private final ConsumerService consumerService;
    private final ScreeningService screeningService;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;


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
        return screening.reserve(consumer, reservationSaveRequestDto.getAudienceCount());
    }

    private void createSeatByAudienceCount(ReservationSaveRequestDto reservationSaveRequestDto, Reservation reservation) {
        for (int count = 0; count < reservationSaveRequestDto.getAudienceCount(); count++) {
            SeatDto.SaveRequestDto saveRequestDto = reservationSaveRequestDto.getSeatSaveRequestDto().get(count);
            updateSeat(reservation, saveRequestDto);
        }
    }

    private void updateSeat(Reservation reservation, SeatDto.SaveRequestDto saveRequestDto) {
        Seat seat = seatService.findSeat(saveRequestDto.getSeatId());
        seat.updateReservationStatus(ReservationStatus.RESERVATION);
        seat.reserve(reservation);
    }

    @Transactional
    public void ticketPublish(Long reservationId) {
        Reservation reservation = findReservation(reservationId);
        Ticket savedTicket = ticketRepository.save(reservation.publishTicket());
        reservation.getConsumer().receive(savedTicket);
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