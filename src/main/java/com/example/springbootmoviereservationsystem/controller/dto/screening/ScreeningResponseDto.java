package com.example.springbootmoviereservationsystem.controller.dto.screening;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

public class ScreeningResponseDto {

    @Getter
    public static class PageScreenResponseDto {

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

        public static ScreeningResponseDto.PageScreenResponseDto of(Page<ScreeningSaveResponseDto> pageScreenResponseDto) {
            return PageScreenResponseDto.builder()
                    .screenPageDto(pageScreenResponseDto)
                    .build();
        }
    }
}
