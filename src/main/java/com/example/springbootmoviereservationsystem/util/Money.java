package com.example.springbootmoviereservationsystem.util;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

// TODO Money 엔티티를 보면 util 과 같은 역할을 하는 것처럼 보이는데 util 클래소로 고려해봐야 할 것 같은데여?
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
