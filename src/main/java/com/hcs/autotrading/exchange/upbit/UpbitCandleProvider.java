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

	private final UpbitInfo upbitInfo;

	@Override
	public String getCandle(String market, int count) {
		String uri = String.format("%s%s?count=%d&market=%s", upbitInfo.url(), upbitInfo.path().get("candle"), count, market);
		return apiExecutor.executeGetMethod(URI.create(uri));
	}
}
