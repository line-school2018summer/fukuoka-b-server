package com.line.fukuokabserver.controller

data class PostUpdateChannelName (
        val name: String
)

data class PostAddFriends (
        val userId: Long,
        val friendId: Long
)

data class PostNewGroup (
        val userIds: List<Long>
)

data class PostUpdateName (
        //Cliend側のRESTのHashmapのデータ型の都合でidがString型になっています
        var id: String,
        var name: String
)