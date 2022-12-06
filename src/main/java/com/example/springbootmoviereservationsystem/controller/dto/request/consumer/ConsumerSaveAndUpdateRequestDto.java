package com.example.springbootmoviereservationsystem.controller.dto.request.consumer;

import com.example.springbootmoviereservationsystem.domain.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerSaveAndUpdateRequestDto {

    @JsonProperty("nickname")
    @NotBlank(message = "공백 없이 입력하세요.")
    private String nickname; // 고객 닉네임

    @JsonProperty("phone_number")
    @NotBlank(message = "공백 없이 입력하세요.")
    @Max(value = 11)
    private String phoneNumber; // 고객 핸드폰 번호

    public Consumer toEntity() {
        return Consumer.builder()
                .nickname(this.nickname)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
