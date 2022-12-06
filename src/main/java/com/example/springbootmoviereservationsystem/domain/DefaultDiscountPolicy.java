package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.reservation.DiscountPolicy;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class DefaultDiscountPolicy implements DiscountPolicy {

    private final List<DiscountCondition> conditions;

    @Override
    public Long calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return 0L;
    }

    abstract protected Long getDiscountAmount(Screening screening);
}