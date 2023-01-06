package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.infra.condition.DiscountCondition;

import java.util.List;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {

    private double percent;

    public PercentDiscountPolicy(List<DiscountCondition> conditions, double percent) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
