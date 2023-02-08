package com.example.movie_reservation.module.screening.policy;

import com.example.movie_reservation.module.screening.policy.condition.DiscountCondition;
import com.example.movie_reservation.infra.util.Money;
import com.example.movie_reservation.module.screening.domain.Screening;

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
