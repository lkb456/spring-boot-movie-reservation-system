package com.example.movie_reservation.module.screening.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageScreenResponseDto {

    private final List<ScreeningSaveResponseDto> elements;
    private final int elementsSize;
    private final int currentPage;
    private final int totalPage;
    private final int pageSize;

    @Builder
    public PageScreenResponseDto(Page<ScreeningSaveResponseDto> screenPageDto) {
        this.elements = screenPageDto.getContent();
        this.elementsSize = elements.size();
        this.currentPage = screenPageDto.getNumber();
        this.totalPage = screenPageDto.getTotalPages();
        this.pageSize = screenPageDto.getSize();
    }

    public static PageScreenResponseDto of(Page<ScreeningSaveResponseDto> pageScreenResponseDto) {
        return PageScreenResponseDto.builder()
                .screenPageDto(pageScreenResponseDto)
                .build();
    }
}
