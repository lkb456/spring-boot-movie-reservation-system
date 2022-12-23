package com.example.springbootmoviereservationsystem.controller.movie;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.fixture.CreateEntity;
import com.example.springbootmoviereservationsystem.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("영화 정보 저장하기 테스트")
    void movieSave() throws Exception {
        MovieRequestDto.MovieSaveDto dto = MovieRequestDto.MovieSaveDto.builder()
                .title("아바타")
                .fee(2000L)
                .runningTime(Duration.ofMinutes(120000))
                .releaseStatus(ReleaseStatus.UN_RELEASE)
                .build();

        Movie movie = CreateEntity.createMovie();
        given(movieRepository.save(any())).willReturn(movie);

        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(String.valueOf(movie.getId())));

        verify(movieRepository).save(any());
    }

    @Test
    @DisplayName("영화 정보 단건 조회하기")
    void movieFind() throws Exception {
        Movie movie = CreateEntity.createMovie();
        given(movieService.findMovie(any())).willReturn(movie);

        mockMvc.perform(get("/movies/" + movie.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MovieResponseDto.MovieSaveDto.of(movie))));

        verify(movieService).findMovie(any());
    }
}