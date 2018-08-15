package com.line.fukuokabserver.model

import java.sql.Timestamp

data class Message(
        var id: Long,
        var senderId: Long,
        var recipientId: Long,
        var text: String,
        var sendAt: Timestamp
)