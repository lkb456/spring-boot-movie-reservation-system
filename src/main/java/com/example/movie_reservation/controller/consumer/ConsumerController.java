package com.example.movie_reservation.controller.consumer;

import com.example.movie_reservation.controller.consumer.dto.ConsumerRequestDto;
import com.example.movie_reservation.controller.consumer.dto.ConsumerResponseDto;
import com.example.movie_reservation.service.ConsumerService;
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


@Api(tags = "고객 정보 API")
@Validated
@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/consumers")
    @Operation(summary = "고객 정보 생성", description = "닉네임과 전화번호를 입력하시면 정보가 생성됩니다. 각 데이터는 유일한 데이터야 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "고객 정보 생성 성공"),
            @ApiResponse(code = 400, message = "중복된 데이터로 인해 실패")
    })
    public ResponseEntity<Long> consumerAdd(@Valid @RequestBody @ApiParam(value = "고객 정보") final ConsumerRequestDto consumerSaveRequestDto) {
        Long savedId = consumerService.saveConsumer(consumerSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/consumers/{id}")
    @Operation(summary = "고객 정보 조회", description = "고객 아이디 식별자를 통해 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "고객 정보 조회 성공"),
            @ApiResponse(code = 400, message = "존재하지 않는 고객정보입니다.")
    })
    public ResponseEntity<ConsumerResponseDto> consumerFind(@PathVariable("id") @ApiParam(value = "고객 아이디(식별자)") final Long consumerId) {
        ConsumerResponseDto dto = ConsumerResponseDto.of(consumerService.findConsumer(consumerId));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/consumers/{id}")
    @Operation(summary = "고객 정보 삭제", description = "고객 아이디 식별자를 통해 데이터를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "고객 정보 삭제 성공"),
            @ApiResponse(code = 400, message = "존재하지 않는 고객정보입니다.")
    })
    public ResponseEntity<Void> consumerLeave(@PathVariable("id") @ApiParam(value = "고객 아이디(식별자)") final Long consumerId) {
        consumerService.leaveConsumer(consumerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
