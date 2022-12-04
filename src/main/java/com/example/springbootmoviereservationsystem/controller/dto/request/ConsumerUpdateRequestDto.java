package com.example.springbootmoviereservationsystem.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ConsumerUpdateRequestDto {

    @NotBlank
    private String nickname; // 닉네임

    @JsonProperty("phone")
    @NotBlank
    @Max(value = 11)
    private String phoneNumber; // 핸드폰 번호
}
