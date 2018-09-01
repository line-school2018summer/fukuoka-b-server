package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.UserDTO
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper {

    @Select(
            """
        SELECT id, name, userId, mail FROM users WHERE id=#{id}
        """
    )
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "userId", column = "userId"),
            Result(property = "mail", column = "mail")
    ))
    fun findById(id: Long): UserDTO

    @Select("SELECT id, name, userId, mail FROM users WHERE userId=#{userId}")
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "userId", column = "userId"),
            Result(property = "mail", column = "mail")
    ))
    fun findByUserId(userId: String): UserDTO

    @Select("SELECT id, name, userId, mail FROM users WHERE id IN (SELECT friendId FROM friends WHERE userId=#{id})")
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "userId", column = "userId"),
            Result(property = "mail", column = "mail")
    ))
    fun getFriends(id: Long): ArrayList<UserDTO>

    @Select("SELECT id, name, userId, mail FROM users WHERE mail=#{mail}")
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "userId", column = "userId"),
            Result(property = "mail", column = "mail")
    ))
    fun findByMail(mail: String): UserDTO

    @Insert("INSERT INTO friends (userId, friendId) VALUES (#{id}, #{friendId})")
    fun addFriend(id: Long, friendId: Long)

//    @Select(
//            """
//        SELECT id, name, email FROM users WHERE name LIKE CONCAT('%', #{searchStr}, '%')
//        """
//    )
//    fun findBySearchStr(searchStr: String): ArrayList<UserDTO>
}