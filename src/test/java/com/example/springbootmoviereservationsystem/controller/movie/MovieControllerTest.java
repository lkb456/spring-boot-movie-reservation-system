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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.util.List;

import static com.example.springbootmoviereservationsystem.fixture.CreateDto.createMovieDtoProjection;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        // given
        MovieRequestDto.MovieSaveDto dto = MovieRequestDto.MovieSaveDto.builder()
                .title("아바타")
                .fee(2000L)
                .runningTime(Duration.ofMinutes(120000))
                .releaseStatus(ReleaseStatus.UN_RELEASE)
                .build();

        Movie movie = CreateEntity.createMovie();
        given(movieRepository.save(any())).willReturn(movie);

        // when && then
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
        // given
        Movie movie = CreateEntity.createMovie();
        given(movieService.findMovie(any())).willReturn(movie);

        // when && then
        mockMvc.perform(get("/movies/" + movie.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MovieResponseDto.MovieSaveDto.of(movie))));

        verify(movieService).findMovie(any());
    }

    @Test
    @DisplayName("영화 검색 페이징 처리하기 테스트")
    void movieSearch() throws Exception {
        // given
        MovieDtoProjection projection = createMovieDtoProjection();
        Page<MovieDtoProjection> page = new PageImpl<>(List.of(projection));
        MovieResponseDto.PageMovieDto pageMovieDto = MovieResponseDto.PageMovieDto.of(page);

        given(movieService.searchMovies(any(), any(), any())).willReturn(pageMovieDto);

        // when && then
        mockMvc.perform(get("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("title", "아")
                        .queryParam("status", "RELEASE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pageMovieDto)));
    }

    @Test
    @DisplayName("영화 정보 업데이트 하기 테스트")
    void movieUpdate() throws Exception {
        MovieRequestDto.MovieUpdateDto updateDto = MovieRequestDto.MovieUpdateDto.builder()
                .title("싸움의 기술")
                .fee(2000L)
                .runningTime(Duration.ofMinutes(12000))
                .releaseStatus(ReleaseStatus.RELEASE)
                .build();

        willDoNothing().given(movieService).updateMovie(any(), any());

        mockMvc.perform(put("/movies/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(movieService).updateMovie(any(), any());
    }

    @Test
    @DisplayName("영화 정보 삭제하기 테스트")
    void movieDelete() throws Exception {
        willDoNothing().given(movieService).deleteMovie(any());

        mockMvc.perform(delete("/movies/" + 1))
                .andDo(print())
                .andExpect(status().isOk());

        verify(movieService).deleteMovie(any());
    }
}