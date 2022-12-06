package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.screening.Screening;

import java.util.List;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {

    private Long discountAmount;

    public AmountDiscountPolicy(List<DiscountCondition> conditions, Long discountAmount) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Long getDiscountAmount(Screening screening) {
        return this.discountAmount;
    }
}
