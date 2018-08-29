package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.UserDTO

interface IUserDAO {
    fun getUser(id: Long): UserDTO

    fun getUsers(ids: List<Long>): List<UserDTO>

    fun getFriendList(userId: Long): List<String>

    fun getUserByMail(mail: String): UserDTO

    fun addUser(user: UserDTO)

    fun addFriend(id1: Long, id2: Long)

    fun updateUser(userId: Long, user: UserDTO)
}