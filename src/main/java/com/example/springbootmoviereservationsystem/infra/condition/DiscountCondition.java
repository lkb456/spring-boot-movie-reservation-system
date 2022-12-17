package com.example.springbootmoviereservationsystem.infra.condition;

import com.example.springbootmoviereservationsystem.domain.screening.Screening;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
