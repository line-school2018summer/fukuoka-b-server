package com.line.fukuokabserver.entity

data class Channel (
        var id: Long,
        var users: List<User>
)