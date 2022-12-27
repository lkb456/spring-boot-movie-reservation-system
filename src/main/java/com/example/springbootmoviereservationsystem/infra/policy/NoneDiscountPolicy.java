package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.domain.money.Money;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
