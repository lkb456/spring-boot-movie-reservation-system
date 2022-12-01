package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerSaveRequestDto {

    @JsonProperty("nick_name")
    @NotBlank(message = "공백 없이 입력하세요.")
    private String nickName;

    @JsonProperty("phone_number")
    @NotBlank(message = "공백 없이 입력하세요.")
    private String phoneNumber;

    public Consumer toEntity() {
        return Consumer.builder()
                .nickname(this.nickName)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
