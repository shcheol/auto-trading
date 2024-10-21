package com.hcs.autotrading.api;

import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientApiExecutor implements ApiExecutor{
	@Override
	public String executeGetMethod(URI uri) {
		return null;
	}

	@Override
	public String executeGetMethod(URI uri, HttpHeaders headers) {
		return null;
	}

	@Override
	public String executePostMethod(URI uri, HttpHeaders headers, String request) {

		HttpRequest build = HttpRequest.newBuilder(uri)
				.setHeader(HttpHeaders.AUTHORIZATION, headers.getFirst(HttpHeaders.AUTHORIZATION))
				.setHeader(HttpHeaders.CONTENT_TYPE, headers.getFirst(HttpHeaders.CONTENT_TYPE))
				.POST(HttpRequest.BodyPublishers.ofString(request))
				.build();

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> send = null;
		try {
			send = httpClient.send(build, HttpResponse.BodyHandlers.ofString());
			return send.body();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
