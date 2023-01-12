package com.example.movie_reservation.infra.policy;

import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.screening.Screening;

@FunctionalInterface
public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
