package com.example.movie_reservation.infra.policy;

import com.example.movie_reservation.util.Money;
import com.example.movie_reservation.domain.screening.Screening;
import com.example.movie_reservation.infra.condition.DiscountCondition;

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
