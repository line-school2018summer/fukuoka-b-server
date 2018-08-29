package com.line.fukuokabserver.controller

data class UserListResponse(
        var id: Long,
        var name: String,
        var email: String
)

data class PostSearchRequest(
        val search_str: String
)

data class PostNewChannel (
        val userIds: List<Long>
)

data class PostAddFriends (
        val id1: Long,
        val id2: Long
)