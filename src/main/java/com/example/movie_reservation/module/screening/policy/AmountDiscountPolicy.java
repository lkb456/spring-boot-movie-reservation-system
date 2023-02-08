package com.example.movie_reservation.module.screening.policy;

import com.example.movie_reservation.module.screening.policy.condition.DiscountCondition;
import com.example.movie_reservation.infra.util.Money;
import com.example.movie_reservation.module.screening.domain.Screening;

import java.util.List;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {

    private Money discountAmount;

    public AmountDiscountPolicy(List<DiscountCondition> conditions, Money discountAmount) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return this.discountAmount;
    }
}
