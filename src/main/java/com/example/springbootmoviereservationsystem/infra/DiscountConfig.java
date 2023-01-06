package com.example.springbootmoviereservationsystem.infra;

import com.example.springbootmoviereservationsystem.util.Money;
import com.example.springbootmoviereservationsystem.infra.condition.PeriodCondition;
import com.example.springbootmoviereservationsystem.infra.policy.AmountDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class DiscountConfig {

    // 기간 조건 (금요일 8시 30분 ~ 23시 59분)
    @Bean
    public PeriodCondition periodCondition() {
        return new PeriodCondition(DayOfWeek.FRIDAY, LocalTime.of(8, 30), LocalTime.of(23, 59));
    }

    // 1000원 할인
    @Bean
    public AmountDiscountPolicy amountDiscountPolicy() {
        return new AmountDiscountPolicy(List.of(periodCondition()), Money.wons(1000));
    }

    // 할인 없음
//    @Bean
//    public NoneDiscountPolicy noneDiscountPolicy() {
//        return new NoneDiscountPolicy();
//    }

    // 10% 할인
//    @Bean
//    public PercentDiscountPolicy percentDiscountPolicy() {
//        return new PercentDiscountPolicy(List.of(periodCondition()), 0.3);
//    }


}
