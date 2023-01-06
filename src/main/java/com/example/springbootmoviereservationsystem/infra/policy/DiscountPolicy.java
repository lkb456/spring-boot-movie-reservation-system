package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;

@FunctionalInterface
public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
