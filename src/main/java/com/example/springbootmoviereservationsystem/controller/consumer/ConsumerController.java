package com.example.springbootmoviereservationsystem.controller.consumer;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerResponseDto;
import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerRequestDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    public ResponseEntity<ConsumerResponseDto> consumerFind(@NotNull @PathVariable("id") final Long consumerId) {
        ConsumerResponseDto consumerResponseDto = ConsumerResponseDto.of(consumerService.findConsumer(consumerId));
        return ResponseEntity.status(HttpStatus.OK).body(consumerResponseDto);
    }

    @DeleteMapping("/consumers/{id}")
    public ResponseEntity<Void> consumerLeave(@NotNull @PathVariable("id") final Long consumerId) {
        consumerService.leaveConsumer(consumerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
