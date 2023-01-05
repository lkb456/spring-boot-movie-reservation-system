package com.example.springbootmoviereservationsystem.controller.screening;

import com.example.springbootmoviereservationsystem.controller.screening.dto.PageScreenResponseDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.fixture.CreateDto;
import com.example.springbootmoviereservationsystem.fixture.CreateEntity;
import com.example.springbootmoviereservationsystem.service.ScreeningService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScreeningController.class)
class ScreeningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScreeningService screeningService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("상영 정보 저장하기 테스트")
    void screenSave() throws Exception {
        // given
        Movie movie = CreateEntity.createMovie();
        Screening screening = CreateEntity.createScreening(movie);

        ScreeningSaveRequestDto saveRequestDto = ScreeningSaveRequestDto.builder()
                .movieId(movie.getId())
                .when(screening.getWhenScreened())
                .build();

        given(screeningService.saveScreen(any())).willReturn(screening);

        // when && then
        mockMvc.perform(post("/screenings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(saveRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(ScreeningSaveResponseDto.of(screening))));

        verify(screeningService).saveScreen(any());
    }

    @Test
    @DisplayName("상영 정보 검색 페이징 조회 테스트")
    void screensSearch() throws Exception {
        // given
        ScreeningSaveResponseDto screeningDtoProjection = CreateDto.createScreeningDtoProjection();
        Page<ScreeningSaveResponseDto> screenDtoProjections = new PageImpl<>(List.of(screeningDtoProjection));
        PageScreenResponseDto result = PageScreenResponseDto.of(screenDtoProjections);

        given(screeningService.searchScreens(any(), any(), any())).willReturn(result);

        // when && then
        mockMvc.perform(get("/screenings")
                        .queryParam("title", "아")
                        .queryParam("when", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(result)));

        verify(screeningService).searchScreens(any(), any(), any());
    }

}