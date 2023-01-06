package com.example.springbootmoviereservationsystem.domain.money;

import com.example.springbootmoviereservationsystem.util.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("머니 생성 테스트")
    void wonsMethodTest() {
        long money = 2000L;
        BigDecimal expectedWons = BigDecimal.valueOf(money);

        Money wons = Money.wons(money);

        assertThat(money).isNotNull();
        assertThat(expectedWons).isEqualTo(wons.getAmount());
    }

    @Test
    @DisplayName("인원수에 따른 돈 계산")
    void MoneyTest() {
        BigDecimal expectedAmount = BigDecimal.valueOf(10000.0);
        int audienceCount = 10;
        Money money = Money.wons(1000);

        Money result = money.times(audienceCount);

        assertThat(expectedAmount).isEqualTo(result.getAmount());
    }
}