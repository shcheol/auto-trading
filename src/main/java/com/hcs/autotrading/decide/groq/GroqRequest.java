package com.hcs.autotrading.decide.groq;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.ArrayList;


@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GroqRequest {
	private final ArrayList<Message> messages = new ArrayList<>();
	private String model = "llama3-8b-8192";
	private int temperature = 0;
	private int max_tokens = 1024;
	private boolean stream = false;
	private ResponseFormat response_format = new ResponseFormat();

	@Getter
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class Message {
		private String role;
		private String content;

		public Message(String role, String content) {
			this.role = role;
			this.content = content;
		}
	}

	@Getter
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class ResponseFormat {
		private final String type = "json_object";
	}
}
