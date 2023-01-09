package com.example.springbootmoviereservationsystem.controller.consumer.dto;

import com.example.springbootmoviereservationsystem.domain.consumer.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerRequestDto {

    @NotBlank(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    @JsonProperty("nickname")
    private String nickname; // 고객 닉네임

    @Length(max = 11, message = "번호는 11자리 이하여야 합니다.")
    @NotBlank(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    @JsonProperty("phone_number")
    private String phoneNumber; // 고객 핸드폰 번호

    public Consumer toEntity() {
        return Consumer.builder()
                .nickname(this.nickname)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
