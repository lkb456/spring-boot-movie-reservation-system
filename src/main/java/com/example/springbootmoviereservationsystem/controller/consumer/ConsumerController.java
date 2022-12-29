package com.example.springbootmoviereservationsystem.controller.consumer;

import com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.example.springbootmoviereservationsystem.controller.consumer.dto.ConsumerResponseDto.ConsumerSaveResponseDto;

@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/consumers")
    public ResponseEntity<Long> consumerAdd(@Valid @RequestBody final ConsumerSaveAndUpdateRequestDto consumerSaveRequestDto) {
        Long savedId = consumerService.saveConsumer(consumerSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerSaveResponseDto> consumerFind(@NotNull @PathVariable("id") final Long consumerId) {
        ConsumerSaveResponseDto consumerSaveResponseDto = ConsumerSaveResponseDto.of(consumerService.findConsumer(consumerId));
        return ResponseEntity.status(HttpStatus.OK).body(consumerSaveResponseDto);
    }

    @DeleteMapping("/consumers/{id}")
    public ResponseEntity<Void> consumerLeave(@NotNull @PathVariable("id") final Long consumerId) {
        consumerService.leaveConsumer(consumerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
