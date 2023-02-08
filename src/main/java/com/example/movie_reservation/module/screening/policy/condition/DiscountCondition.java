package com.example.movie_reservation.module.screening.policy.condition;

import com.example.movie_reservation.module.screening.domain.Screening;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
