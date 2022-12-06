package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.request.consumer.ConsumerSaveAndUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.consumer.ConsumerSaveResponseDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
<<<<<<< HEAD
import jakarta.validation.Valid;
=======
>>>>>>> feature/test
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import javax.validation.Valid;

>>>>>>> feature/test
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
    public ResponseEntity<ConsumerSaveResponseDto> consumerFind(@RequestParam(name = "phone") String phoneNumber) {
        ConsumerSaveResponseDto consumerSaveResponseDto = ConsumerSaveResponseDto.of(consumerService.findConsumer(phoneNumber));
        return ResponseEntity.status(HttpStatus.OK).body(consumerSaveResponseDto);
    }
}
