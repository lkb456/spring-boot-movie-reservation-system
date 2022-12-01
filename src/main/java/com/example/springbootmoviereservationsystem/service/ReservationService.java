package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.request.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ReservationSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.consumer.ConsumerRepository;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.reservation.ReservationRepository;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConsumerRepository consumerRepository;
    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationSaveResponseDto reserveSave(Long screeningId, ReservationSaveRequestDto reservationSaveRequestDto) {
        Screening screening = findScreen(screeningId);
        Reservation reservation = screening.reserve(
                findConsumer(reservationSaveRequestDto),
                reservationSaveRequestDto.getAudienceCount());
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationSaveResponseDto.of(savedReservation);
    }

    private Screening findScreen(Long screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보입니다."));
    }

    private Consumer findConsumer(ReservationSaveRequestDto reservationSaveRequestDto) {
        return consumerRepository.findByPhoneNumber(reservationSaveRequestDto.getPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
