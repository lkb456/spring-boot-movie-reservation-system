package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.consumer.ConsumerResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.consumer.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Valid
@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/consumers")
    public ResponseEntity<Long> consumerAdd(@RequestBody ConsumerSaveAndUpdateRequestDto consumerSaveRequestDto) {
        Long savedId =  consumerService.saveConsumer(consumerSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/consumers")
    public ResponseEntity<ConsumerResponseDto.ConsumerSaveResponseDto> consumerFind(@RequestParam(name = "phone") String phoneNumber) {
        ConsumerResponseDto.ConsumerSaveResponseDto consumerSaveResponseDto = ConsumerResponseDto.ConsumerSaveResponseDto.of(consumerService.findConsumer(phoneNumber));
        return ResponseEntity.status(HttpStatus.OK).body(consumerSaveResponseDto);
    }
}
