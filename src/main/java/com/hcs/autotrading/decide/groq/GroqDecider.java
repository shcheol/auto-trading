package com.hcs.autotrading.decide.groq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcs.autotrading.api.ApiExecutor;
import com.hcs.autotrading.decide.Decider;
import com.hcs.autotrading.decide.Decision;
import com.hcs.autotrading.decide.DecisionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroqDecider implements Decider {

	private final ApiExecutor apiExecutor;

	private final ObjectMapper om;

	private final GroqInfo groqInfo;

	@Override
	public Decision decide(String data) {
		GroqRequest groqRequest = new GroqRequest();
		groqRequest.getMessages().add(new GroqRequest.Message("system", "You are a professional coin investor. Give your opinion based on the candle information provided. buy/sell/hold. JSON format {\"dicision\":\"BUY\", \"reason\":\"some reason\"}"));
		groqRequest.getMessages().add(new GroqRequest.Message("user", data.replace("\"", "\\\"")));

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + groqInfo.apiKey());
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

		try {

			String request = om.writeValueAsString(groqRequest);
			String s = apiExecutor.executePostMethod(URI.create(groqInfo.url()), headers, request);

			GroqResponse groqResponse = om.readValue(s, GroqResponse.class);
			String content = groqResponse.getChoices().get(0).getMessage().getContent();
			DecisionResponse decisionResponse = om.readValue(content, DecisionResponse.class);
			log.info("decision {}", decisionResponse);
			return decisionResponse.decision();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
