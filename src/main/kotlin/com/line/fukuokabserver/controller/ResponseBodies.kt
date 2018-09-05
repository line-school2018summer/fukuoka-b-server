package com.line.fukuokabserver.controller

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.dto.UserDTO

data class ResponsePersonalChannelInfo(
        val friend: UserDTO,
        val channel: ChannelDTO
)

data class ResponseGroupChannelInfo (
        val users: List<UserDTO>,
        val channel: ChannelDTO
)