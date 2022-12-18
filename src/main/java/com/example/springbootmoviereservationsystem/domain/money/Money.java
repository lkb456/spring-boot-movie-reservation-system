package com.example.springbootmoviereservationsystem.domain.money;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    @Transient
    public static final Money ZERO = Money.wons(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MONEY_ID")
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Builder
    public Money(BigDecimal amount) {
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

    public Money times(int audienceCount) {
        return Money.builder()
                .amount(this.amount.multiply(BigDecimal.valueOf(audienceCount)))
                .build();

    }
}
