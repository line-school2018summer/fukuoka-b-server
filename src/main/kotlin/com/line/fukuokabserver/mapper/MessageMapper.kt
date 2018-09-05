package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.MessageDTO
import org.apache.ibatis.annotations.*

@Mapper
interface MessageMapper {
    @Insert("INSERT INTO messages (channelId, senderId, content, createdAt) " +
            "VALUES (#{channelId,jdbcType=BIGINT}, #{senderId,jdbcType=BIGINT}, #{content,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true)
    fun addMessage(message: MessageDTO)

    @Select("SELECT id, channelId, senderId, content, createdAt FROM messages WHERE channelId=#{channelId}")
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "channelId", column = "channelId"),
            Result(property = "senderId", column = "senderId"),
            Result(property = "content", column = "content"),
            Result(property = "createdAt", column = "createdAt")
    ))
    fun getMessages(channelId: Long): ArrayList<MessageDTO>

}