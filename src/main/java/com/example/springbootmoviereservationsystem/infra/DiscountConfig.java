package com.example.springbootmoviereservationsystem.infra;

import com.example.springbootmoviereservationsystem.domain.reservation.DiscountPolicy;
import com.example.springbootmoviereservationsystem.infra.condition.*;
import com.example.springbootmoviereservationsystem.infra.policy.AmountDiscountPolicy;
import com.example.springbootmoviereservationsystem.infra.policy.NoneDiscountPolicy;
import com.example.springbootmoviereservationsystem.infra.policy.PercentDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class DiscountConfig {

    // 기간 조건 (금요일 8시 30분 ~ 23시 59분)
    @Bean
    public DiscountCondition periodCondition() {
        return new PeriodCondition(DayOfWeek.FRIDAY, LocalTime.of(8, 30), LocalTime.of(23, 59));
    }

    // 할인 없음
    @Bean
    public DiscountPolicy noneDiscountPolicy() {
        return new NoneDiscountPolicy();
    }

    // 10% 할인
    @Bean
    public DiscountPolicy percentDiscountPolicy() {
        return new PercentDiscountPolicy(List.of(periodCondition()), 0.1);
    }

    // 1000원 할인
    @Bean
    public DiscountPolicy amountDiscountPolicy() {
        return new AmountDiscountPolicy(List.of(periodCondition()), 1000L);
    }
}
