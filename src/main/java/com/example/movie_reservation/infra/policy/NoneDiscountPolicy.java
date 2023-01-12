package com.example.movie_reservation.infra.policy;

import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.screening.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
