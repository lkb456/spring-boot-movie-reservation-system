package com.example.springbootmoviereservationsystem.controller.main;

import com.example.springbootmoviereservationsystem.controller.main.dto.SearchMovieOrActorResponseDto;
import com.example.springbootmoviereservationsystem.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class MainController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<SearchMovieOrActorResponseDto> searchMovieOrActor(@RequestParam String keyword) {
        SearchMovieOrActorResponseDto searchMovieOrActorResponseDto = searchService.searchMovieOrActor(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(searchMovieOrActorResponseDto);
    }
}
