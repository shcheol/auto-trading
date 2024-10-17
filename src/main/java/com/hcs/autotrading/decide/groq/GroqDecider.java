package com.hcs.autotrading.decide.groq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcs.autotrading.api.ApiExecutor;
import com.hcs.autotrading.decide.Decider;
import com.hcs.autotrading.decide.Decision;
import com.hcs.autotrading.decide.DecisionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class GroqDecider implements Decider {

	private final ApiExecutor apiExecutor;

	@Value("${groq.api-key}")
	private String apiKey;

	@Override
	public Decision decide(String data) {

		String message = """
				{
				          "messages": [
				            {
				              "role": "system",
				              "content": "\\nYou are a professional coin investor. Give your opinion based on the candle information provided. buy/sell/hold. \\nJSON format {\\"dicision\\":\\"BUY\\", \\"reason\\":\\"some reason\\"}"
				            },
				            {
				              "role": "user",
				              "content": "this is candle data: %s"
				            }
				          ],
				          "model": "llama3-8b-8192",
				          "temperature": 1,
				          "max_tokens": 1024,
				          "top_p": 1,
				          "stream": false,
				          "response_format": {
				            "type": "json_object"
				          },
				          "stop": null
				        }
				""".formatted(data.replace("\"", "\\\""));


		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

		String s = apiExecutor.executePostMethod(URI.create("https://api.groq.com/openai/v1/chat/completions"), headers, message);
		System.out.println("s = " + s);
		try {
			GroqResponse groqResponse = new ObjectMapper().readValue(s, GroqResponse.class);
			String content = groqResponse.choices.get(0).message.content;
			System.out.println(content);
			DecisionResponse decisionResponse = new ObjectMapper().readValue(content, DecisionResponse.class);
			System.out.println("decisionResponse = " + decisionResponse.decision());

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
return Decision.BUY;
	}
}
