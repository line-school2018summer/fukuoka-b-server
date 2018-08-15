package com.line.fukuokabserver.controller

import com.line.fukuokabserver.model.MessageTest
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.*


@Controller
class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    fun sendHello(message: MessageTest) : MessageTest {
        return MessageTest(message.from,"Hello World")
    }

}