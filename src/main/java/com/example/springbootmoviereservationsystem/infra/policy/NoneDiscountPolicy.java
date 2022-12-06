package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.domain.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Long calculateDiscountAmount(Screening screening) {
        return 0L;
    }
}
