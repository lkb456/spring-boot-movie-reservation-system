package com.example.movie_reservation.infra.condition;

import com.example.movie_reservation.domain.screening.Screening;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
