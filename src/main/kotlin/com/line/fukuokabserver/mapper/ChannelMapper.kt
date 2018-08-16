package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.ChannelDTO
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options

@Mapper
interface ChannelMapper {
    @Insert("INSERT INTO rooms (id, room_name) VALUES (null, #{room_name}")
    @Options(useGeneratedKeys = true)
    fun addChannel(channel: ChannelDTO): Int
}
