package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.UserDTO
import com.line.fukuokabserver.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserDAO (private val userMapper: UserMapper): IUserDAO {
    override fun getPersonalChannelId(id1: Long, id2: Long): Long {
        return userMapper.getPersonalChannelId(id1, id2)!!
    }

    override fun isPersonalChannelExist(id1: Long, id2: Long): Boolean {
        if (userMapper.getPersonalChannelId(id1, id2) != null) return true
        return false
    }

    override fun addPersonalChannel(id1: Long, id2: Long, channelId: Long) {
        userMapper.addPersonalChannel(id1, id2, channelId)
    }

    override fun getUsers(ids: List<Long>): List<UserDTO> {
        return ids.map { getUser(it) }.toList()
    }

    override fun getUserByMail(mail: String): UserDTO {
        return userMapper.findByMail(mail)
    }

    override fun getUser(id: Long): UserDTO {
        return userMapper.findById(id)
    }

    fun getUserByUserId(userId: String): UserDTO {
        return userMapper.findByUserId(userId)
    }

    override fun getFriendList(userId: Long): List<UserDTO> {
        return userMapper.getFriends(userId)
    }

    override fun addUser(user: UserDTO) {

    }

    override fun addFriend(id1: Long, id2: Long) {
        userMapper.addFriend(id1, id2)
        userMapper.addFriend(id2, id1)
    }

    override fun updateUser(user: UserDTO) {
        userMapper.updateUser(user)
    }
}