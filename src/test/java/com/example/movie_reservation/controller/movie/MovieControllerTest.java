package com.example.movie_reservation.controller.movie;

import com.example.movie_reservation.controller.movie.dto.MovieRequestDto;
import com.example.movie_reservation.controller.movie.dto.MovieResponseDto;
import com.example.movie_reservation.controller.movie.dto.MovieResponsePageDto;
import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.movie.MovieRepository;
import com.example.movie_reservation.domain.movie.ReleaseStatus;
import com.example.movie_reservation.fixture.CreateDto;
import com.example.movie_reservation.fixture.CreateEntity;
import com.example.movie_reservation.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
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
        Long expectedId = 1L;
        given(movieService.saveMovie(any())).willReturn(expectedId);

        // when && then

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CreateDto.createMovieRequestDto())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(expectedId)));

        verify(movieService).saveMovie(any());
    }

    @Test
    @DisplayName("영화 정보 저장하기 테스트 -> 유효성 검사에 맞지 않는 데이터를 넘길 경우")
    void movieSave_param_not_valid() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(MovieRequestDto.builder()
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(MethodArgumentNotValidException.class));
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
                .andExpect(content().json(mapper.writeValueAsString(MovieResponseDto.of(movie))));

        verify(movieService).findMovie(any());
    }

    @Test
    @DisplayName("영화 정보 단건 조회하기 -> pathVariable 이 null 인 경우")
    void movieFind_path_param_not_valid() throws Exception {
        mockMvc.perform(get("/movies/" + null))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(MethodArgumentTypeMismatchException.class));
    }

    @Test
    @DisplayName("영화 검색 페이징 처리하기 테스트")
    void movieSearch() throws Exception {
        // given
        MovieResponsePageDto result = MovieResponsePageDto
                .of(new PageImpl<>(CreateDto.createMovieResponseDtos()));
        given(movieService.searchMovies(any(), any())).willReturn(result);

        // when && then
        mockMvc.perform(get("/movies")
                        .queryParam("title", "아")
                        .queryParam("status", ReleaseStatus.RELEASE.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(result)));

        verify(movieService).searchMovies(any(), any());
    }

    @Test
    @DisplayName("영화 정보 업데이트 하기 테스트")
    void movieUpdate() throws Exception {
        MovieRequestDto updateDto = MovieRequestDto.builder()
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