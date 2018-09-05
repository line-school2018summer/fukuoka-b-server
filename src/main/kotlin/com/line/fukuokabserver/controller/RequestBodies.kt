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
        val userId: Long,
        val friendId: Long
)

data class PostNewGroup (
        val userIds: List<Long>
)