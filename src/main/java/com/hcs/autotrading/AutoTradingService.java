package com.hcs.autotrading;

import com.hcs.autotrading.alert.AlertService;
import com.hcs.autotrading.decide.Decider;
import com.hcs.autotrading.decide.Decision;
import com.hcs.autotrading.exchange.CandleProvider;
import com.hcs.autotrading.exchange.OrderResult;
import com.hcs.autotrading.exchange.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AutoTradingService {

	private final CandleProvider candleProvider;
	private final Decider decider;
	private final OrderService orderService;
	private final AlertService alertService;

	public void doAutoTrade() {

		// 1. getchartdata
		String candle = candleProvider.getCandle("KRW-BTC", 30);

		// 2. buy/sell/hold 판단
		Decision decision = decider.decide(candle);
		System.out.println("decide = " + decision);

		// 3. 실제 자동 매매 진행
		OrderResult order = orderService.order("", decision, BigDecimal.ONE);

		System.out.println("order = " + order);
		// 4. slack bot 알림
		alertService.sendMessage(order.toString(), "");
	}
}
