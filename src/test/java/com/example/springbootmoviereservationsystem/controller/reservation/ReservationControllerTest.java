package com.example.springbootmoviereservationsystem.controller.reservation;

import com.example.springbootmoviereservationsystem.controller.reservation.dto.ReservationResponseDto;
import com.example.springbootmoviereservationsystem.controller.reservation.dto.ReservationSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.seat.SeatDto;
import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.reservation.Reservation;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.fixture.CreateEntity;
import com.example.springbootmoviereservationsystem.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("상영 영화 예약하기 테스트")
    void reserve() throws Exception {
        // given
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reserve = screening.reserve(consumer, 5);

        ReservationResponseDto reservationResponseDto = ReservationResponseDto.of(reserve);

        given(reservationService.reserveSave(any())).willReturn(reservationResponseDto);

        // when && then
        mockMvc.perform(post("/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ReservationSaveRequestDto.builder()
                        .screeningId(1L)
                        .consumerId(1L)
                        .audienceCount(5)
                        .seatSaveRequestDto(List.of(SeatDto.SaveRequestDto.builder()
                                .seatId(1L)
                                .build()))
                        .build())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(reservationResponseDto)));

        verify(reservationService).reserveSave(any());
    }

    @Test
    @DisplayName("예약한 영화 티켓 발행하기")
    void createTicket() throws Exception {
        willDoNothing().given(reservationService).ticketPublish(any());

        mockMvc.perform(post("/reservation/" + 1 + "/ticket"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(reservationService).ticketPublish(any());
    }

    @Test
    @DisplayName("예약한 영화 취소하기")
    void cancel() throws Exception {
        willDoNothing().given(reservationService).cancelReservation(any());

        mockMvc.perform(put("/reservation/" + 1 + "/cancel"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(reservationService).cancelReservation(any());
    }

    @Test
    @DisplayName("예약 정보 단건 조회 하기")
    void findReservation() throws Exception {
        Consumer consumer = CreateEntity.createConsumer();
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);
        Reservation reserve = screening.reserve(consumer, 5);

        ReservationResponseDto reservationResponseDto = ReservationResponseDto.of(reserve);

        given(reservationService.findReservation(any())).willReturn(reserve);

        mockMvc.perform(get("/reservation/" + 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(reservationResponseDto)));

        verify(reservationService).findReservation(any());
    }
}