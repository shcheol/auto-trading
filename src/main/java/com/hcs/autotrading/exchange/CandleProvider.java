package com.hcs.autotrading.exchange;

public interface CandleProvider {

	String getCandle(String market, int count);
}
