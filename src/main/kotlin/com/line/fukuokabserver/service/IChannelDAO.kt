package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.ChannelDTO

interface IChannelDAO {
    fun getChannel(channelId: Long): ChannelDTO

    fun getChannelList(userId: Long): List<ChannelDTO>

    fun addChannel(userIds: List<Long>): ChannelDTO
}