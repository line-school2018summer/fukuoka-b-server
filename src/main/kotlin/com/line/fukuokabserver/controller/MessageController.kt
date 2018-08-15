package com.line.fukuokabserver.controller

import com.line.fukuokabserver.entity.MessageOut
import com.line.fukuokabserver.entity.MessageTest
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*


@Controller
class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    fun sendHello(message: MessageTest) : MessageOut {
        return MessageOut(message.from,"Hello World", "")
    }

    @MessageMapping("/chat.{channelId}")
    @SendTo("/topic/chat.{channelId}")
    fun sendMessage(@DestinationVariable channelId: String, message: MessageTest): MessageOut {
        val time = SimpleDateFormat("HH:mm").format(Date())
        return MessageOut(message.from, message.text, time)
    }
}