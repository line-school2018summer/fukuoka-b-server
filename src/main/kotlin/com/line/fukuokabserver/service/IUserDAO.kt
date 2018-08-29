package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.UserDTO

interface IUserDAO {
    fun getUser(id: Long): UserDTO

    fun getFriendList(userId: Long): List<String>

    fun getUserByMail(mail: String): UserDTO

    fun addUser(user: UserDTO)

    fun addFriend(id: Long, friendId: Long)

    fun updateUser(userId: Long, user: UserDTO)
}