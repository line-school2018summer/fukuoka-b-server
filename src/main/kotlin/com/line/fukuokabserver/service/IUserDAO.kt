package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.UserDTO

interface IUserDAO {
    fun getUser(userId: Long): UserDTO?

    fun getFriendList(userId: Long): List<String>

    fun addUser(user: UserDTO)

    fun updateUser(userId: Long, user: UserDTO)
}