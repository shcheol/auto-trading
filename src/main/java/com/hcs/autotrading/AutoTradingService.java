package com.hcs.autotrading;

import com.hcs.autotrading.alert.AlertService;
import com.hcs.autotrading.decide.Decider;
import com.hcs.autotrading.decide.Decision;
import com.hcs.autotrading.exchange.CandleProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoTradingService {

	private final CandleProvider candleProvider;
	private final Decider decider;
	private final AlertService alertService;

	public void doAutoTrade() {

		// 1. getchartdata
		String candle = candleProvider.getCandle("KRW-BTC", 30);

		// 2. buy/sell/hold 판단
		Decision decide = decider.decide(candle);
		System.out.println("decide = " + decide);

		// 3. 실제 자동 매매 진행

		// 4. slack bot 알림
		alertService.sendMessage("", "");
	}
}
