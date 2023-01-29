package com.example.movie_reservation.controller.consumer;

import com.example.movie_reservation.controller.consumer.dto.ConsumerRequestDto;
import com.example.movie_reservation.controller.consumer.dto.ConsumerResponseDto;
import com.example.movie_reservation.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/consumers")
    public ResponseEntity<Long> consumerAdd(@Valid @RequestBody final ConsumerRequestDto consumerSaveRequestDto) {
        Long savedId = consumerService.saveConsumer(consumerSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerResponseDto> consumerFind(@PathVariable("id") final Long consumerId) {
        ConsumerResponseDto dto = ConsumerResponseDto.of(consumerService.findConsumer(consumerId));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/consumers/{id}")
    public ResponseEntity<Void> consumerLeave(@PathVariable("id") final Long consumerId) {
        consumerService.leaveConsumer(consumerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
