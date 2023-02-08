package com.example.movie_reservation.module.screening.policy;

import com.example.movie_reservation.infra.util.Money;
import com.example.movie_reservation.module.screening.domain.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
