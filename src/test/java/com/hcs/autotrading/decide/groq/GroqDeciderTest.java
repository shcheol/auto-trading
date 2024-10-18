package com.hcs.autotrading.decide.groq;

import com.hcs.autotrading.decide.Decision;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroqDeciderTest {

	@Autowired
	GroqDecider decider;

	@Test
	void decide() {
		Decision decide = decider.decide("");
		assertThat(Decision.values()).contains(decide);
	}
}