package com.hcs.autotrading.api;

import org.springframework.http.HttpHeaders;

import java.net.URI;

public interface ApiExecutor {

	String executeGetMethod(URI uri);

	String executeGetMethod(URI uri, HttpHeaders headers);

	String executePostMethod(URI uri, HttpHeaders headers, String request);


}
