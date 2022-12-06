package com.example.springbootmoviereservationsystem.domain.reservation;

import com.example.springbootmoviereservationsystem.domain.screening.Screening;

@FunctionalInterface
public interface DiscountPolicy {

    Long calculateDiscountAmount(Screening screening);
}
