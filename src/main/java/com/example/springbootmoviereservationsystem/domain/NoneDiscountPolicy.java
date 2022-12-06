package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.reservation.DiscountPolicy;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Long calculateDiscountAmount(Screening screening) {
        return 0L;
    }
}
