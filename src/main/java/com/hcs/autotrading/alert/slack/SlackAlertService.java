package com.hcs.autotrading.alert.slack;

import com.hcs.autotrading.alert.AlertService;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SlackAlertService implements AlertService {

	private final SlackInfo slackInfo;

	@Override
	public void sendMessage(String message, String channel) {
		MethodsClient methods = Slack.getInstance().methods(slackInfo.token());

		ChatPostMessageRequest request = ChatPostMessageRequest.builder()
				.channel(StringUtils.hasText(channel) ? channel : slackInfo.channel())
				.text(message)
				.build();

		try {
			ChatPostMessageResponse chatPostMessageResponse = methods.chatPostMessage(request);
			System.out.println("chatPostMessageResponse = " + chatPostMessageResponse);
		} catch (IOException | SlackApiException e) {
			throw new RuntimeException(e);
		}
	}
}
