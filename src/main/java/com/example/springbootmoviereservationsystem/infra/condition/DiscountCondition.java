package com.example.springbootmoviereservationsystem.infra.condition;

import com.example.springbootmoviereservationsystem.domain.Screening;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
