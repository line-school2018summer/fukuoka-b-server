package com.line.fukuokabserver.service

import com.line.fukuokabserver.dto.UserDTO
import com.line.fukuokabserver.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserDAO (private val userMapper: UserMapper): IUserDAO {

    override fun getUser(userId: Long): UserDTO {
        return userMapper.findByUserId(userId)
    }

    override fun getFriendList(userId: Long): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUser(userId: Long, user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}