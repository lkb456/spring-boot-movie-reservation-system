package com.example.springbootmoviereservationsystem.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageMovieResponseDto {

    private final List<MovieResponseDto> elements;
    private final int elementsSize;
    private final int currentPage;
    private final int totalPage;
    private final int pageSize;

    @Builder
    private PageMovieResponseDto(Page<MovieResponseDto> movieResponseDtoPage) {
        this.elements = movieResponseDtoPage.getContent();
        this.elementsSize = elements.size();
        this.currentPage = movieResponseDtoPage.getNumber();
        this.totalPage = movieResponseDtoPage.getTotalPages();
        this.pageSize = movieResponseDtoPage.getSize();
    }

    public static PageMovieResponseDto of(Page<MovieResponseDto> movieResponseDtoPage) {
        return PageMovieResponseDto.builder()
                .movieResponseDtoPage(movieResponseDtoPage)
                .build();
    }
}
