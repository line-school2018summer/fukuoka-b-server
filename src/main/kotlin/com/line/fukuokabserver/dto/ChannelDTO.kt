package com.line.fukuokabserver.dto

import com.line.fukuokabserver.entity.User

class ChannelDTO (
        var id: Long,
        var users: List<User>
)