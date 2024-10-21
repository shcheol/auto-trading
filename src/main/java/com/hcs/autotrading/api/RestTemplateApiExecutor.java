package com.hcs.autotrading.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class RestTemplateApiExecutor implements ApiExecutor {

	private final RestTemplate restTemplate;

	@Override
	public String executeGetMethod(URI uri) {
		return restTemplate.getForObject(uri, String.class);
	}

	@Override
	public String executeGetMethod(URI uri, HttpHeaders headers) {
		return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
	}

	@Override
	public String executePostMethod(URI uri, HttpHeaders headers, String request) {
		HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
		return restTemplate.postForObject(uri, httpEntity, String.class);
	}

}
