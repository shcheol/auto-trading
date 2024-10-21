package com.hcs.autotrading.exchange;

import com.hcs.autotrading.decide.Decision;

import java.math.BigDecimal;

public record OrderResult(Decision decision, boolean success, BigDecimal balance) {

	@Override
	public String toString() {
		return decision + "주문 ["+ balance + "] " + (success?"성공":"실패") + "했습니다.";
	}
}
