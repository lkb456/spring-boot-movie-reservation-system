package com.example.movie_reservation.module.screening.policy;

import com.example.movie_reservation.module.screening.policy.condition.DiscountCondition;
import com.example.movie_reservation.infra.util.Money;
import com.example.movie_reservation.module.screening.domain.Screening;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class DefaultDiscountPolicy implements DiscountPolicy {

    private final List<DiscountCondition> conditions;

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}