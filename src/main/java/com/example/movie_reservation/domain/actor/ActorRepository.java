package com.example.movie_reservation.domain.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("select a from Actor a where concat(a.firstName, a.lastName) like %:keyword%")
    List<Actor> findByFirstNameContainingOrLastNameContainingOrderByFirstName(@Param("keyword") String keyword);
}
