package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.dto.UserDTO

interface IChannelDAO {
    fun getChannel(channelId: Long): ChannelDTO

    fun getChannelList(userId: Long): List<ChannelDTO>

    fun addChannel(channel:ChannelDTO, userIds: List<Long>): ChannelDTO

    fun addChannelAttend(channelId: Long, userId: Long)

    fun getPublicChannel(): List<ChannelDTO>

    fun getChannelAttendees(channelId: Long): List<UserDTO>
}