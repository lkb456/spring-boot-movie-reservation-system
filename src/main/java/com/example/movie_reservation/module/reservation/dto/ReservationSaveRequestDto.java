package com.example.movie_reservation.module.reservation.dto;

import com.example.movie_reservation.module.seat.dto.SeatRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "예매 정보", description = "상영영화 식별자, 고객 식별자, 예매 인원 수, 좌석 예매 리스트")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSaveRequestDto {

    @NotNull
    @JsonProperty("screen_id")
    @ApiModelProperty(value = "상영영화 식별자", example = "1")
    private Long screeningId;

    @NotNull
    @JsonProperty("consumer_id")
    @ApiModelProperty(value = "고객 식별자", example = "1")
    private Long consumerId;

    @Max(value = 10, message = "최대 예매 인원 수는 10명입니다.")
    @JsonProperty("audience_count")
    @ApiModelProperty(value = "예매 인원 수", example = "5")
    private int audienceCount;

    @JsonProperty("seat")
    @ApiModelProperty(value = "좌석 예매 리스트")
    private List<SeatRequestDto> seatSaveRequestDto;

}
