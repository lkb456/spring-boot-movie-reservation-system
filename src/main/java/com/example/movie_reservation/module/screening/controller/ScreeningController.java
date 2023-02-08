package com.example.movie_reservation.module.screening.controller;

import com.example.movie_reservation.module.screening.dto.PageScreenResponseDto;
import com.example.movie_reservation.module.screening.dto.ScreeningSaveRequestDto;
import com.example.movie_reservation.module.screening.dto.ScreeningSaveResponseDto;
import com.example.movie_reservation.module.screening.service.ScreeningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Api(tags = "영화 상영정보 API")
@Validated
@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @PostMapping("/screenings")
    @Operation(summary = "영화 상영정보 생성", description = "영화 식별자와, 상영 시간을 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 상영정보 생성 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<ScreeningSaveResponseDto> screenSave(@Valid
                                                               @RequestBody
                                                               @ApiParam(value = "영화 상영정보") final ScreeningSaveRequestDto screeningSaveRequestDto) {
        ScreeningSaveResponseDto screeningSaveResponseDto =
                ScreeningSaveResponseDto.of(screeningService.saveScreen(screeningSaveRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningSaveResponseDto);
    }

    @GetMapping("/screenings")
    @Operation(summary = "영화 상영정보 페이징", description = "해당 키워드를 포함하고 있는 상영영화 정보와 선택 시간 이후에 있는 상영영화 정보를 페이징 처리하여 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 상영정보 페이징 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<PageScreenResponseDto> screensSearch(@RequestParam(value = "title", required = false)
                                                               @ApiParam(value = "키워드") final String title,
                                                               @RequestParam(value = "when", required = false)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm")
                                                               @ApiParam(value = "상영 시간") final LocalDateTime whenScreened,
                                                               @PageableDefault Pageable pageable) {
        PageScreenResponseDto pageScreenResponseDto = screeningService.searchScreens(title, whenScreened, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageScreenResponseDto);
    }
}
