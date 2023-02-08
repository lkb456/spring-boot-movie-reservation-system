package com.example.movie_reservation.module.actor.controller.dto;

import com.example.movie_reservation.module.actor.domain.Actor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel(value = "영화배우 정보", description = "성, 이름, 생년월일, 사진, 이력내용")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorRequestDto {

    @NotBlank
    @JsonProperty("first_name")
    @ApiModelProperty(value = "이름", example = "동석")
    private String firstName;

    @NotBlank
    @JsonProperty("last_name")
    @ApiModelProperty(value = "성", example = "마")
    private String lastName;

    @NotNull
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "생년월일", example = "2022-01-01")
    private LocalDate birthDate;

    @NotNull
    @JsonProperty
    @ApiModelProperty(value = "이미지", example = "image.png")
    private String image;

    @NotNull
    @JsonProperty
    @ApiModelProperty(value = "이력 내용", example = "범죄도시 메인 배우")
    private String content;

    public Actor toEntity() {
        return Actor.builder()
                .lastName(this.lastName)
                .firstName(this.firstName)
                .birthDate(this.birthDate)
                .image(this.image)
                .content(this.content)
                .build();
    }
}
