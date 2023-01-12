package com.example.movie_reservation.util;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Money {

    public static final Money ZERO = Money.wons(0);

    private BigDecimal amount;

    @Builder
    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(long amount) {
        return Money.builder()
                .amount(BigDecimal.valueOf(amount))
                .build();
    }

    public static Money wons(double amount) {
        return Money.builder()
                .amount(BigDecimal.valueOf(amount))
                .build();
    }

    public Money times(double percent) {
        return Money.builder()
                .amount(this.amount.multiply(BigDecimal.valueOf(percent)))
                .build();
    }

    public Money minus(Money amount) {
        return Money.builder()
                .amount(this.amount.subtract(amount.amount))
                .build();
    }
}
