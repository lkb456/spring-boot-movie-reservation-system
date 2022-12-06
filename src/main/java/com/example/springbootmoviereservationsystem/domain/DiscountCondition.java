package com.example.springbootmoviereservationsystem.domain;

import com.example.springbootmoviereservationsystem.domain.screening.Screening;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
