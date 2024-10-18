package com.hcs.autotrading.decide.groq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroqResponse {

	private int created;
	private String model;
	private final List<Choice> choices = new ArrayList<>();

	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Choice {
		private Message message;
	}

	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Message {
		private String role;
		private String content;
	}
}


