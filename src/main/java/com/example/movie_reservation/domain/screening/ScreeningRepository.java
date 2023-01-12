package com.example.movie_reservation.domain.screening;

import com.example.movie_reservation.controller.screening.dto.ScreeningSaveResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query(value = "select " +
            "new com.example.movie_reservation.controller.screening.dto.ScreeningSaveResponseDto(s.id, m, s.whenScreened) " +
            "from Screening s " +
            "join s.movie m " +
            "where m.title like :title% " +
            "or s.whenScreened >=:when")
    Page<ScreeningSaveResponseDto> findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(@Param("title") String title,
                                                                                               @Param("when") LocalDateTime whenScreened,
                                                                                               Pageable pageable);

    @EntityGraph(value = "screeningWithMovie", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Screening> findById(Long screenId);
}
