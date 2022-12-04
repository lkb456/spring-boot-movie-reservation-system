package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.response.ConsumerDetailResponseDto;
import com.example.springbootmoviereservationsystem.service.ConsumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final ConsumerService consumerService;

    @GetMapping("/admin/consumers/{phone}")
    public ResponseEntity<ConsumerDetailResponseDto> consumerDetail(@Valid
                                                @Length(max = 11)
                                                @PathVariable(name = "phone") String phoneNumber) {
        ConsumerDetailResponseDto consumerDetailResponseDto =
                ConsumerDetailResponseDto.of(consumerService.findConsumer(phoneNumber));
        return ResponseEntity.status(HttpStatus.OK).body(consumerDetailResponseDto);
    }


}
