package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.request.ConsumerUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.ConsumerDetailResponseDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Valid
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final ConsumerService consumerService;

    @GetMapping("/admin/consumers/{phone}")
    public ResponseEntity<ConsumerDetailResponseDto> consumerDetail(@Length(max = 11)
                                                                    @PathVariable(name = "phone") String phoneNumber) {
        ConsumerDetailResponseDto consumerDetailResponseDto =
                ConsumerDetailResponseDto.of(consumerService.findConsumer(phoneNumber));
        return ResponseEntity.status(HttpStatus.OK).body(consumerDetailResponseDto);
    }

    @PutMapping("/admin/consumer/{phone}")
    public ResponseEntity<Void> consumerUpdate(@PathVariable(name = "phone") String phoneNumber,
                                               @RequestBody ConsumerUpdateRequestDto consumerUpdateRequestDto) {
        consumerService.updateConsumer(phoneNumber, consumerUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
