package com.example.movie_reservation.module.main.controller;

import com.example.movie_reservation.module.main.dto.SearchMovieOrActorResponseDto;
import com.example.movie_reservation.module.main.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "메인 검색 API")
@Validated
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    @Operation(summary = "키워드로 영화 또는 영화배우 리스트들을 조회한다.", description = "키워드로 영화, 영화배우 리스트를 가져온다." +
            "\n 리스트가 없다면 빈 컬렉션을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공")
    })
    public ResponseEntity<SearchMovieOrActorResponseDto> searchMovieOrActor(@RequestParam @ApiParam(value = "키워드") String keyword) {
        SearchMovieOrActorResponseDto searchMovieOrActorResponseDto = searchService.searchMovieOrActor(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(searchMovieOrActorResponseDto);
    }
}
