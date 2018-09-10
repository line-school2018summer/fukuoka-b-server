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
    fun getPersonalChannel(@PathVariable("userId") userId:Long, @PathVariable("friendId") friendId: Long): ResponseChannelInfo {
        if (userService.isPersonalChannelExist(userId, friendId)) {
            val channel = channelService.getChannel(userService.getPersonalChannelId(userId, friendId))
            val friend = userService.getUser(friendId)
            val me = userService.getUser(userId)
            return ResponseChannelInfo(listOf(friend, me), channel)
        }
        else {
            var user = userService.getUser(userId)
            var user2 = userService.getUser(friendId)
            var channel = ChannelDTO(null, "${user.name}と${user2.name}", "PERSONAL")
            channelService.addChannel(channel, listOf(user.Id, user2.Id))
            userService.addPersonalChannel(userId, friendId, channel.id!!)

            return ResponseChannelInfo(listOf(user2, user), channel)
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
    fun newGroupChannel(@RequestBody request: PostNewGroup): ResponseChannelInfo {
        val users = userService.getUsers(request.userIds)
        var defaultName = ""
        for (i in 0..users.size-1) {
            defaultName += users[i].name
            if (i != users.size-1) defaultName += ", "
        }
        var channel = ChannelDTO(null, defaultName, "GROUP")
        channelService.addChannel(channel, request.userIds)
        return ResponseChannelInfo(users, channel)
    }

    @GetMapping(
            value = ["chat/{channelId}/info"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getChannelInfo(@PathVariable("channelId") channelId: Long): ResponseChannelInfo {
        val channel = channelService.getChannel(channelId)
        val users = channelService.getChannelAttendees(channelId)
        return ResponseChannelInfo(users, channel)
    }


    @GetMapping(
            value = ["chat/{userId}/channels"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getChannelsByUser(@PathVariable("userId") userId: Long): List<ChannelDTO> {
        return channelService.getChannelList(userId)
    }
}