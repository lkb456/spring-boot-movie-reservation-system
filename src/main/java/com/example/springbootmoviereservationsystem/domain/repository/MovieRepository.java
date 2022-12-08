package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select " +
            "m.title," +
            " m.fee," +
            " m.runningTime," +
            " m.releaseStatus " +
            "from Movie m " +
            "where m.title like %:title% " +
            "or m.releaseStatus =:status")
    Page<MovieResponseDto.MovieDto> findByTitleLikeAndReleaseMovie(@Param("title") String title,
                                   @Param("status") ReleaseStatus status,
                                   Pageable pageable,
                                   Class<MovieResponseDto.MovieDto> movieDtoClass);
}
