package com.hcs.autotrading.exchange.upbit;

import com.hcs.autotrading.api.ApiExecutor;
import com.hcs.autotrading.decide.Decision;
import com.hcs.autotrading.exchange.OrderResult;
import com.hcs.autotrading.exchange.OrderService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpbitOrderService implements OrderService {

	private final ApiExecutor apiExecutor;

	private final UpbitInfo upbitInfo;

	@Override
	public OrderResult order(String market, Decision decision, BigDecimal volume) {

		String account = upbitInfo.url() + upbitInfo.path().get("account");

		String jwtToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.claim("access_key", upbitInfo.accessKey())
				.claim("nonce", UUID.randomUUID().toString())
				.signWith(SignatureAlgorithm.HS256, upbitInfo.secretKey().getBytes(StandardCharsets.UTF_8))
				.compact();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);

		String s = apiExecutor.executeGetMethod(URI.create(account), httpHeaders);
		System.out.println("s = " + s);
		return new OrderResult(decision, true, volume);
	}
}
