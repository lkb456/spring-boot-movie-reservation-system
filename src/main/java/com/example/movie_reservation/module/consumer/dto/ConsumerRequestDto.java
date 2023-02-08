package com.example.movie_reservation.module.consumer.dto;

import com.example.movie_reservation.module.consumer.domain.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "고객 정보", description = "닉네임, 전화번호")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerRequestDto {

    @NotBlank(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    @JsonProperty("nickname")
    @ApiModelProperty(value = "고객 닉네임", example = "대림동 불주먹")
    private String nickname;

    @Length(max = 11, message = "번호는 11자리 이하여야 합니다.")
    @NotBlank(message = "공백 또는 빈칸을 입력하면 안됩니다.")
    @JsonProperty("phone_number")
    @ApiModelProperty(value = "고객 핸드폰 번호", example = "01012341234")
    private String phoneNumber;

    public Consumer toEntity() {
        return Consumer.builder()
                .nickname(this.nickname)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
