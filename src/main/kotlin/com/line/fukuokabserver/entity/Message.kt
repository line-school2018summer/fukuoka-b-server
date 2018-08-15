package com.line.fukuokabserver.entity

import com.line.fukuokabserver.dto.MessageDTO
import java.sql.Timestamp

data class Message(
        var id: Long,
        var senderId: Long,
        var roomId: Long,
        var text: String,
        var sendAt: Timestamp
) {
    constructor(dto: MessageDTO): this(dto.id, dto.senderId, dto.roomId, dto.text, dto.sendAt)
}