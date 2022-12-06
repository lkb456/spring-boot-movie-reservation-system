package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.domain.Screening;
import com.example.springbootmoviereservationsystem.infra.condition.DiscountCondition;

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
