package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.dto.UserDTO
import com.line.fukuokabserver.mapper.ChannelMapper
import org.springframework.stereotype.Service

@Service
class ChannelDAO (private val channelMapper: ChannelMapper): IChannelDAO {
    override fun updateChannel(channel: ChannelDTO) {
        channelMapper.updateChannel(channel)
    }

    override fun getChannelAttendees(channelId: Long): List<UserDTO> {
        return channelMapper.getChannelAttendees(channelId)
    }

    override fun addChannel(channel: ChannelDTO, userIds: List<Long>): ChannelDTO {
        channelMapper.addChannel(channel)
        userIds.forEach { id ->
            channelMapper.addChannelAttend(channel.id!!, id)
        }
        return channel
    }

    override fun addChannelAttend(channelId: Long, userId: Long) {
        channelMapper.addChannelAttend(channelId, userId)
    }

    override fun getChannel(channelId: Long): ChannelDTO {
        return channelMapper.getChannel(channelId)
    }

    override fun getPublicChannel(): List<ChannelDTO> {
        return channelMapper.getPublicChannelList()
    }

    override fun getChannelList(userId: Long): List<ChannelDTO> {
        return channelMapper.getAttendedChannels(userId)
    }
}