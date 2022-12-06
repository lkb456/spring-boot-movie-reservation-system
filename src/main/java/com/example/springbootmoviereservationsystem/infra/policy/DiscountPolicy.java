package com.example.springbootmoviereservationsystem.infra.policy;

import com.example.springbootmoviereservationsystem.domain.Screening;

@FunctionalInterface
public interface DiscountPolicy {

    Long calculateDiscountAmount(Screening screening);
}
