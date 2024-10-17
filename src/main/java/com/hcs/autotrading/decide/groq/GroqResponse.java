package com.hcs.autotrading.decide.groq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroqResponse {

	public int created;
	public String model;
	public List<Choice> choices = new ArrayList<>();

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Choice {
		public Message message;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Message {
		public String role;
		public String content;
	}
}


