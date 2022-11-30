package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.ConsumerSaveRequestDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Valid
@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/consumer")
    public ResponseEntity<Long> consumerAdd(@RequestBody ConsumerSaveRequestDto consumerSaveRequestDto) {
        Long savedId =  consumerService.saveConsumer(consumerSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }
}
