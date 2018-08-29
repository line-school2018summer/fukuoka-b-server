package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.UserDTO
import com.line.fukuokabserver.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserDAO (private val userMapper: UserMapper): IUserDAO {
    override fun getUserByMail(mail: String): UserDTO {
        return userMapper.findByMail(mail)
    }

    override fun getUser(id: Long): UserDTO {
        return userMapper.findById(id)
    }

    fun getUserByUserId(userId: String): UserDTO {
        return userMapper.findByUserId(userId)
    }

    override fun getFriendList(userId: Long): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addFriend(id: Long, friendId: Long) {
        userMapper.addFriend(id, friendId)
    }

    override fun updateUser(userId: Long, user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}