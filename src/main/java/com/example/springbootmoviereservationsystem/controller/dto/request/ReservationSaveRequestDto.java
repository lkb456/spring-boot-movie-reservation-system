package com.example.springbootmoviereservationsystem.controller.dto.request;

import com.example.springbootmoviereservationsystem.controller.dto.SeatDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSaveRequestDto {

    @NotNull
    @JsonProperty("screening_id")
    private Long screeningId; // 상영 정보

    @NotBlank(message = "공백 없이 입력하세요.")
    @JsonProperty("phone")
    private String phoneNumber; // 고객 휴대폰 번호

    @Max(value = 10, message = "최대 예매 인원 수는 10명입니다.")
    @JsonProperty("audience_count")
    private int audienceCount; // 예매 인원 수

    @JsonProperty("seat")
    private List<SeatDto.SaveRequestDto> seatSaveRequestDto; // 좌석 예메 정보

}
