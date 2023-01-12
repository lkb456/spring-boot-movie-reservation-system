package com.example.movie_reservation.controller.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MovieResponsePageDto {

    private final List<MovieResponseDto> elements;
    private final int elementsSize;
    private final int currentPage;
    private final int totalPage;
    private final int pageSize;

    @Builder
    private MovieResponsePageDto(Page<MovieResponseDto> movieResponseDtoPage) {
        this.elements = movieResponseDtoPage.getContent();
        this.elementsSize = elements.size();
        this.currentPage = movieResponseDtoPage.getNumber();
        this.totalPage = movieResponseDtoPage.getTotalPages();
        this.pageSize = movieResponseDtoPage.getSize();
    }

    public static MovieResponsePageDto of(Page<MovieResponseDto> movieResponseDtoPage) {
        return MovieResponsePageDto.builder()
                .movieResponseDtoPage(movieResponseDtoPage)
                .build();
    }
}
