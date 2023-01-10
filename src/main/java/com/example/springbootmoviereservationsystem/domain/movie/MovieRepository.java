package com.example.springbootmoviereservationsystem.domain.movie;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select m from Movie m where m.title like %:keyword% order by m.title")
    Page<MovieResponseDto> findByTitleContainingOrderByTitle(@Param("keyword") String keyword, Pageable pageable);

    Optional<Movie> findById(Long movieId);
}
