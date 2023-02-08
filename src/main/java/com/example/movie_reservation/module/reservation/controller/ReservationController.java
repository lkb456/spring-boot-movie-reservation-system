package com.example.movie_reservation.module.reservation.controller;

import com.example.movie_reservation.module.reservation.dto.PopularMovieResponseDto;
import com.example.movie_reservation.module.reservation.dto.ReservationResponseDto;
import com.example.movie_reservation.module.reservation.dto.ReservationSaveRequestDto;
import com.example.movie_reservation.module.reservation.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "예매 정보 API")
@Validated
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    @Operation(summary = "예매 정보 생성", description = "영화 식별자와, 상영 시간, 좌석 리스트를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예매정보 생성 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<ReservationResponseDto> reserve(@Valid
                                                          @RequestBody
                                                          @ApiParam(value = "예매정보")
                                                          ReservationSaveRequestDto reservationSaveRequestDto) {
        ReservationResponseDto reservationSaveResponseDto = reservationService.reserveSave(reservationSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSaveResponseDto);
    }

    @PostMapping("/reservation/{id}/ticket")
    @Operation(summary = "예매 티켓 생성", description = "예매 정보에 대한 티켓을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "티켓 생성 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<Void> createTicket(@PathVariable("id")
                                             @ApiParam(value = "예매 식별자") Long reservationId) {
        reservationService.ticketPublish(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/reservation/{id}/cancel")
    @Operation(summary = "예매 취소", description = "예매를 취소합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "발행된 티켓 취소 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<Void> cancelReserve(@PathVariable("id")
                                              @ApiParam(value = "예매 식별자") Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/reservation/{id}")
    @Operation(summary = "예매 정보 조회", description = "예매 식별자로 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예매정보 조회 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<ReservationResponseDto> findReservation(@PathVariable("id")
                                                                  @ApiParam(value = "예매 식별자") Long reservationId) {
        ReservationResponseDto reservationResponseDto = ReservationResponseDto.of(reservationService.findReservation(reservationId));
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponseDto);
    }

    @GetMapping("/reservation/popular")
    @Operation(summary = "예매 1위인 영화정보를 가져온다", description = "예매 1위인 영화를 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공")
    })
    public ResponseEntity<List<PopularMovieResponseDto>> findPopularMovie() {
        List<PopularMovieResponseDto> popularMovieResponseDto = reservationService.bestMovieFind();
        return ResponseEntity.status(HttpStatus.OK).body(popularMovieResponseDto);
    }
}
