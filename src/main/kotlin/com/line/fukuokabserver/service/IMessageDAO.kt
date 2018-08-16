package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.MessageDTO

interface IMessageDAO {
    fun addMessage(message: MessageDTO)
}