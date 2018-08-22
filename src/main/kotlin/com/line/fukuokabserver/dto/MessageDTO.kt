package com.line.fukuokabserver.dto

import java.sql.Timestamp

data class MessageDTO (
        var id: Long?,
        var channelId: Long,
        var senderId: Long,
        var content: String,
        var createdAt: Timestamp?
)