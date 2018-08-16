package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.mapper.ChannelMapper
import org.springframework.stereotype.Service

@Service
class ChannelDAO (private val channelMapper: ChannelMapper): IChannelDAO {
    override fun addChannel(userIds: List<Long>): ChannelDTO {
        var channel = ChannelDTO(null, "", userIds)
        channelMapper.addChannel(channel)
        return channel
    }

    override fun getChannel(channelId: Long): ChannelDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChannelList(userId: Long): List<ChannelDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}