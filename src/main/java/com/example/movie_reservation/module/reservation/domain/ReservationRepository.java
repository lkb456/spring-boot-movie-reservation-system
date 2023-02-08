package com.example.movie_reservation.module.reservation.domain;

import com.example.movie_reservation.module.reservation.dto.PopularMovieResponseDto;
import com.example.movie_reservation.module.screening.domain.Screening;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @EntityGraph(value = "reserveWithAll", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Reservation> findById(Long reservationId);

    @Query(value = "select new com.example.movie_reservation.module.reservation.dto.PopularMovieResponseDto(r.screening.movie, count(r.screening.movie.title)) " +
            "from Reservation r " +
            "join r.screening " +
            "join r.screening.movie " +
            "group by r.screening.movie.title " +
            "order by count(r.screening.movie.title) desc")
    List<PopularMovieResponseDto> findBestMovie(Pageable pageable);

    @EntityGraph(attributePaths = "seats", type = EntityGraph.EntityGraphType.FETCH)
    List<Reservation> findByScreening(Screening screening);
}
