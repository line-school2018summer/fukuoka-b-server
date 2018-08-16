package com.line.fukuokabserver.entity

data class Channel (
        var id: Long,
        var room_name: String,
        var userIds: List<Long>
)