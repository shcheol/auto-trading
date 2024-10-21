package com.hcs.autotrading.exchange;

import com.hcs.autotrading.decide.Decision;

import java.math.BigDecimal;

public interface OrderService {

    OrderResult order(String market, Decision decision, BigDecimal volume);
}
