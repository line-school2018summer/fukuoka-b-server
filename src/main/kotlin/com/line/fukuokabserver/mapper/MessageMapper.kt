package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.MessageDTO
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options

@Mapper
interface MessageMapper {
    @Insert("INESRT INTO messages (id, room_id, send_user_id, content, created_at)" +
            "VALUES (null, #{roomId}, #{senderId}, #{text}, #{sendAt})")
    @Options(useGeneratedKeys = true)
    fun addMessage(message: MessageDTO)
}