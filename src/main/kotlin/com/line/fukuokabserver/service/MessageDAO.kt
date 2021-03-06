package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.MessageDTO
import com.line.fukuokabserver.mapper.MessageMapper
import org.springframework.stereotype.Service

@Service
class MessageDAO (private val messageMapper: MessageMapper): IMessageDAO {
    override fun getChannelMessages(channelId: Long): List<MessageDTO> {
        return messageMapper.getMessages(channelId)
    }

    override fun addMessage(message: MessageDTO) {
        messageMapper.addMessage(message)
    }


}