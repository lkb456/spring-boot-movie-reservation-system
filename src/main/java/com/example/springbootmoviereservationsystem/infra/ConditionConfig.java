package com.example.springbootmoviereservationsystem.infra;

import com.example.springbootmoviereservationsystem.domain.DiscountCondition;
import com.example.springbootmoviereservationsystem.domain.PeriodCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    @Bean
    public DiscountCondition condition() {
        return new PeriodCondition();
    }
}
