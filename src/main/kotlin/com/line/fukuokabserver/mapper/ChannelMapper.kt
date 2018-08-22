package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.ChannelDTO
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param

@Mapper
interface ChannelMapper {
    @Insert("INSERT INTO channels (id, name) VALUES (null, #{name}" )
    @Options(useGeneratedKeys = true)
    fun addChannel(channel: ChannelDTO): Int

    @Insert("INSERT INTO channelAttend (channelId, userId) VALUES (#{channelId}, #{userId})")
    fun addChannelAttend(@Param("channelId") channelId: Long, @Param("userId") userId: Long)
}
