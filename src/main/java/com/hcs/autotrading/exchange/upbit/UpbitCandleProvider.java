package com.hcs.autotrading.exchange.upbit;

import com.hcs.autotrading.api.ApiExecutor;
import com.hcs.autotrading.exchange.CandleProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class UpbitCandleProvider implements CandleProvider {

	private final ApiExecutor apiExecutor;

	@Override
	public String getCandle(String market, int count) {


		return apiExecutor.executeGetMethod(URI.create("https://api.upbit.com/v1/candles/minutes/1?count=" + count + "&market=" + market));
	}
}
