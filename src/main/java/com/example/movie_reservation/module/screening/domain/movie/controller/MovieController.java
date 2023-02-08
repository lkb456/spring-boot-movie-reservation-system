package com.example.movie_reservation.module.screening.domain.movie.controller;

import com.example.movie_reservation.module.screening.domain.movie.dto.MovieRequestDto;
import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponseDto;
import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponsePageDto;
import com.example.movie_reservation.module.screening.domain.movie.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "영화 정보 API")
@Validated
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    @Operation(summary = "영화 정보 생성", description = "제목, 요금, 상영 시간, 개봉 상태 정보를 저장합니다." +
            "\n- 상영 시간은 초단위로 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 정보 생성 성공"),
            @ApiResponse(code = 400, message = "요청 데이터 유효성 검사 실패")
    })
    public ResponseEntity<Long> movieSave(@Valid @RequestBody @ApiParam(value = "영화 정보") final MovieRequestDto movieRequestDto) {
        Long savedMovieId = movieService.saveMovie(movieRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieId);
    }

    @GetMapping("/movies/{id}")
    @Operation(summary = "영화 정보 조회", description = "영화 정보 식별자로 데이터를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 정보 조회 성공"),
            @ApiResponse(code = 400, message = "식별자가 존재 하지 않아 실패")
    })
    public ResponseEntity<MovieResponseDto> movieFind(@PathVariable("id") @ApiParam(value = "영화 식별자") final Long movieId) {
        MovieResponseDto movieResponseDto = MovieResponseDto.of(movieService.findMovie(movieId));
        return ResponseEntity.status(HttpStatus.OK).body(movieResponseDto);
    }

    @GetMapping("/movies")
    @Operation(summary = "영화 정보 페이징", description = "해당 키워드가 있는 영화 목록을 조회하고 제목을 기준으로 오름차순 정렬 후 페이칭 처리한다." +
            "\n 해당 키워드가 없는 경우 빈 컬렉션이 BODY 에 내려온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 정보 페이징 처리 성공"),
            @ApiResponse(code = 400, message = "데이터 바인딩 실패")
    })
    public ResponseEntity<MovieResponsePageDto> movieSearch(@RequestParam(value = "title", required = false)
                                                            @ApiParam(value = "키워드") final String title,
                                                            @PageableDefault Pageable pageable) {
        MovieResponsePageDto movieResponsePageDto = movieService.searchMovies(title, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponsePageDto);
    }

    @PutMapping("/movies/{id}")
    @Operation(summary = "영화 정보 수정", description = "식별자를 통해 조회하고 업데이트를 진행한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 정보 수정 성공"),
            @ApiResponse(code = 400, message = "식별자가 존재 하지 않아 실패")
    })
    public ResponseEntity<Void> movieUpdate(@PathVariable("id") final @ApiParam(value = "영화 식별자") Long movieId,
                                            @Valid
                                            @RequestBody
                                            @ApiParam(value = "영화 수정 정보") final MovieRequestDto movieRequestDto) {
        movieService.updateMovie(movieId, movieRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/movies/{id}")
    @Operation(summary = "영화 정보 삭제", description = "식별자를 통해 조회하고 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화 정보 삭제 성공"),
            @ApiResponse(code = 400, message = "식별자가 존재 하지 않아 실패")
    })
    public ResponseEntity<Void> movieDelete(@PathVariable("id") @ApiParam(value = "영화 식별자") final Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/movies/actors-add")
    @Operation(summary = "영화 정보에 영화배우 추가", description = "식별자를 통해 조회하고 영화정보에 영화배우를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "영화배우 저장 성공"),
            @ApiResponse(code = 400, message = "식별자가 존재 하지 않아 실패")
    })
    public ResponseEntity<Void> addMovieActor(@RequestParam @ApiParam(value = "영화배우 식별자") Long actorId,
                                              @RequestParam @ApiParam(value = "영화 식별자") Long movieId) {
        movieService.movieActorAdd(actorId, movieId);
        return ResponseEntity.ok().build();
    }
}