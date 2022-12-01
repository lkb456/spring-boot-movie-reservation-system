package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.request.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ReservationSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConsumerService consumerService;
    private final ScreeningService screeningService;

    @Transactional
    public ReservationSaveResponseDto reserveSave(Long screeningId, ReservationSaveRequestDto reservationSaveRequestDto) {
        Consumer consumer = consumerService.findConsumer(reservationSaveRequestDto.getPhoneNumber());
        Screening screening = screeningService.findScreen(screeningId);
        Reservation reservation = screening.reserve(consumer, reservationSaveRequestDto.getAudienceCount());
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationSaveResponseDto.of(savedReservation);
    }
}