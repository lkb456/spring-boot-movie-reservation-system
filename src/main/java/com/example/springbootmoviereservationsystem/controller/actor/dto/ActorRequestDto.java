package com.example.springbootmoviereservationsystem.controller.actor.dto;

import com.example.springbootmoviereservationsystem.domain.actor.Actor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorRequestDto {

    @NotBlank
    @JsonProperty("first_name")
    private String firstName; // 성

    @NotBlank
    @JsonProperty("last_name")
    private String lastName; // 이름

    @NotNull
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate; // 생년월일

    @NotNull
    @JsonProperty
    private String image; // 사진

    @NotNull
    @JsonProperty
    private String content; // 배우 이력 내용

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
