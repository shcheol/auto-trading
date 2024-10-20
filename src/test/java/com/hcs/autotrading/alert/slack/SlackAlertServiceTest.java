package com.hcs.autotrading.alert.slack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SlackAlertServiceTest {

    @Autowired
    SlackAlertService slackAlertService;

    @Test
    void send(){
        slackAlertService.sendMessage("hi","#자동매매");
    }

}