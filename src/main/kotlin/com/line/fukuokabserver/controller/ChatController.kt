package com.line.fukuokabserver.controller

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.dto.MessageDTO
import com.line.fukuokabserver.entity.MessageOut
import com.line.fukuokabserver.entity.MessageTest
import com.line.fukuokabserver.service.ChannelDAO
import com.line.fukuokabserver.service.MessageDAO
import com.line.fukuokabserver.service.UserDAO
import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@Controller
@RestController
class ChatController(private val channelService: ChannelDAO, private val messageService: MessageDAO, private val userService: UserDAO) {
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

    @GetMapping(
            value = ["/chat/personal/{userId}/{friendId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getPersonalChannel(@PathVariable("userId") userId:Long, @PathVariable("friendId") friendId: Long): ResponsePersonalChannelInfo {
        if (userService.isPersonalChannelExist(userId, friendId)) {
            val channel = channelService.getChannel(userService.getPersonalChannelId(userId, friendId))
            val friend = userService.getUser(friendId)
            return ResponsePersonalChannelInfo(friend, channel)
        }
        else {
            var user = userService.getUser(userId)
            var user2 = userService.getUser(friendId)
            var channel = ChannelDTO(null, "${user.name}と${user2.name}", "PERSONAL")
            channelService.addChannel(channel, listOf(user.Id, user2.Id))
            userService.addPersonalChannel(userId, friendId, channel.id!!)

            return ResponsePersonalChannelInfo(user2, channel)
        }
    }

    @GetMapping(
            value=["/chat/public"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun publicChannel(): List<ChannelDTO> {
        return channelService.getPublicChannel()
    }

    @GetMapping(
            value = ["/chat/messages/{channelId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessages(@PathVariable("channelId") channelId: Long): List<MessageDTO> {
        return messageService.getChannelMessages(channelId)
    }

    @PostMapping(
            value = ["/chat/group/new"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun newGroupChannel(@RequestBody request: PostNewGroup): ResponseGroupChannelInfo {
        var channel = ChannelDTO(null, "NOT YET", "GROUP")
        channelService.addChannel(channel, request.userIds)
        val users = userService.getUsers(request.userIds)
        return ResponseGroupChannelInfo(users, channel)
    }
}