package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.ChannelDTO

interface IChannelDAO {
    fun getChannel(channelId: Long): ChannelDTO

    fun getChannelList(userId: Long): List<ChannelDTO>

    fun addChannel(channel:ChannelDTO): ChannelDTO

    fun addChannelAttend(channelId: Long, userId: Long)

    fun getPublicChannel(): List<ChannelDTO>
}