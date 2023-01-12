package com.example.movie_reservation.infra.policy;

import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.screening.Screening;
import com.example.movie_reservation.infra.condition.DiscountCondition;

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
