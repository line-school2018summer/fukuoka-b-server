package com.line.fukuokabserver.controller

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.dto.MessageDTO
import com.line.fukuokabserver.entity.MessageOut
import com.line.fukuokabserver.entity.MessageTest
import com.line.fukuokabserver.service.ChannelDAO
import com.line.fukuokabserver.service.MessageDAO
import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class PostNewChannel (
        val userIds: List<Long>
)

@Controller
@RestController
class ChatController(private val channelService: ChannelDAO, private val messageService: MessageDAO) {
    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    fun sendHello(message: MessageTest) : MessageOut {
        return MessageOut(message.from,"Hello World", "")
    }

    @MessageMapping("/chat.{channelId}/test")
    @SendTo("/topic/chat.{channelId}/test")
    fun sendMessage(@DestinationVariable channelId: String, message: MessageTest): MessageOut {
        val time = SimpleDateFormat("HH:mm").format(Date())
        return MessageOut(message.from, message.text, time)
    }

    @MessageMapping("/chat.{channelId}")
    @SendTo("/topic/chat.{channelId}")
    fun sendMessage(@DestinationVariable channelId: String, message: MessageDTO): MessageDTO {
        val time = Timestamp(Date().time)
        message.createdAt = time
        messageService.addMessage(message)
        return message
    }

    @PostMapping(
            value = ["/chat/new"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun newChannel(@RequestBody request: PostNewChannel): Long {
        return channelService.addChannel(request.userIds).id!!
    }

    @GetMapping(
            value=["/chat/public"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun publicChannel(): List<ChannelDTO> {
        return channelService.getPublicChannel()
    }
}