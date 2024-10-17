package com.hcs.autotrading;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AutoTradingServiceTest {

	@Autowired
	AutoTradingService autoTradingService;

	@Test
	void doAutoTrade(){
		autoTradingService.doAutoTrade();
	}

}