package com.example.springbootmoviereservationsystem.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootmoviereservationsystem.controller.dto.MovieResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select new " +
            "com.example.springbootmoviereservationsystem.controller.dto.MovieResponseDto" +
            "(m.title," +
            " m.fee," +
            " m.runningTime," +
            " m.releaseStatus) " +
            "from Movie m " +
            "where m.title like %:title% " +
            "or m.releaseStatus =:status")
    Page<MovieResponseDto> findByTitleLikeAndReleaseMovie(@Param("title") String title,
                                                          @Param("status") ReleaseStatus status,
                                                          Pageable pageable);
}
