package com.example.movie_reservation.controller.actor;

import com.example.movie_reservation.controller.actor.dto.ActorRequestDto;
import com.example.movie_reservation.controller.actor.dto.ActorResponseDto;
import com.example.movie_reservation.service.actor.ActorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "영화배우 정보 API")
@RestController
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @PostMapping("/actors")
    @Operation(summary = "영화배우 정보 생성", description = "성, 이름, 생년월일, 사진, 내용")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화배우 정보 생성 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<ActorResponseDto> createActor(@Valid
                                                        @RequestBody
                                                        @ApiParam(value = "영화배우 정보")
                                                        ActorRequestDto actorRequestDto) {
        ActorResponseDto actorResponseDto = actorService.saveActor(actorRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(actorResponseDto);
    }
}
