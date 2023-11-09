package com.example.drill.controller;

import com.example.drill.domain.dto.MessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompController {

    @MessageMapping("/greeting/{channelId}")
    @SendTo("/topic/greeting/{channelId}")
    public MessageVO handle(@DestinationVariable String channelId, MessageVO vo) {
        log.info("channelId : {} | msg : {}", channelId, vo);
        return vo;
    }

}
