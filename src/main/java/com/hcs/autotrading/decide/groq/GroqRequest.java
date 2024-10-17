package com.hcs.autotrading.decide.groq;

import java.util.ArrayList;


public class GroqRequest {
	public ArrayList<Message> messages = new ArrayList<>();
	public String model;
	public int temperature;
	public int max_tokens;
	public int top_p;
	public boolean stream;
	public ResponseFormat response_format;
	public Object stop;

	public static class Message {
		public String role;
		public String content;
	}

	public static class ResponseFormat {
		public String type;
	}
}
