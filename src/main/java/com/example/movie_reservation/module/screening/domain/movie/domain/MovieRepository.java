package com.example.movie_reservation.module.screening.domain.movie.domain;

import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select m from Movie m where m.title like %:keyword% order by m.title")
    Page<MovieResponseDto> findByTitleContainingOrderByTitle(@Param("keyword") String keyword, Pageable pageable);

    Optional<Movie> findById(Long movieId);
    List<Movie> findByTitleContainingOrderByTitle(String keyword);
}
