package com.example.springbootmoviereservationsystem.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> feature/test
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

>>>>>>> feature/test
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSaveRequestDto {

    @JsonProperty("phone")
    @NotBlank(message = "공백 없이 입력하세요.")
    private String phoneNumber; // 고객 휴대폰 번호

    @JsonProperty("audience_count")
    @Max(value = 10, message = "최대 예매 인원 수는 10명입니다.")
    private int audienceCount; // 예매 인원 수
}
