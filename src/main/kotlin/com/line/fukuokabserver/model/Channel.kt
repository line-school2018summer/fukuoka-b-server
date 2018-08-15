package com.line.fukuokabserver.model

data class Channel (
        var id: Long,
        var users: List<User>
)