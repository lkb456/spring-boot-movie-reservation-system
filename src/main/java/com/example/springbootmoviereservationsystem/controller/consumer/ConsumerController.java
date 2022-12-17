package com.example.springbootmoviereservationsystem.controller.consumer;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerResponseDto.ConsumerSaveResponseDto;

@Valid
@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/consumers")
    public ResponseEntity<Long> consumerAdd(@RequestBody ConsumerSaveAndUpdateRequestDto consumerSaveRequestDto) {
        Long savedId = consumerService.saveConsumer(consumerSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerSaveResponseDto> consumerFind(@PathVariable("id") Long consumerId) {
        ConsumerSaveResponseDto consumerSaveResponseDto = ConsumerSaveResponseDto.of(consumerService.findConsumer(consumerId));
        return ResponseEntity.status(HttpStatus.OK).body(consumerSaveResponseDto);
    }
}
