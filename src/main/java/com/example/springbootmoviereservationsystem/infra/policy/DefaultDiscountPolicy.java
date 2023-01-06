package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.infra.condition.DiscountCondition;
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