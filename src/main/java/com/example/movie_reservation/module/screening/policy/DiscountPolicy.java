package com.example.movie_reservation.module.screening.policy;

import com.example.movie_reservation.infra.util.Money;
import com.example.movie_reservation.module.screening.domain.Screening;

@FunctionalInterface
public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
