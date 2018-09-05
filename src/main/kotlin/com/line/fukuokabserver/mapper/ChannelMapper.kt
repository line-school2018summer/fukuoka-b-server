package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.ChannelDTO
import org.apache.ibatis.annotations.*

@Mapper
interface ChannelMapper {
    @Insert("INSERT INTO channels (name, type) VALUES (#{name}, #{type})" )
    @Options(useGeneratedKeys = true)
    fun addChannel(channel: ChannelDTO): Int

    @Insert("INSERT INTO channelAttend (channelId, userId) VALUES (#{channelId}, #{userId})")
    fun addChannelAttend(@Param("channelId") channelId: Long, @Param("userId") userId: Long)

    @Select("SELECT * from channels WHERE name LIKE 'PUBLIC%'")
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "type", column = "type")
    ))
    fun getPublicChannelList(): List<ChannelDTO>

    @Select("SELECT * from channels WHERE id = #{channelId}")
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "type", column = "type")
    ))
    fun getChannel(channelId: Long): ChannelDTO
}
