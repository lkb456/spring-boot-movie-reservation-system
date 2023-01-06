package com.example.springbootmoviereservationsystem.domain.money;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

// TODO Money 엔티티를 보면 util 과 같은 역할을 하는 것처럼 보이는데 util 클래소로 고려해봐야 할 것 같은데여?
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
