package com.line.fukuokabserver.controller

import com.line.fukuokabserver.dto.ChannelDTO
import com.line.fukuokabserver.dto.UserDTO

data class ResponseChannelInfo (
        val users: List<UserDTO>,
        val channel: ChannelDTO
)