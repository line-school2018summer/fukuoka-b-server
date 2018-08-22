package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.UserDTO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {

    @Select(
            """
        SELECT id, name, userId, mail FROM users WHERE id=#{userId}
        """
    )
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "userId", column = "userId"),
            Result(property = "mail", column = "mail")
    ))
    fun findByUserId(userId: Long): UserDTO

    @Select()
    @Results(value = arrayOf(
            Result(id = true, property = "id", column = "id"),
            Result(property = "name", column = "name"),
            Result(property = "userId", column = "userId"),
            Result(property = "mail", column = "mail")
    ))
    fun getFriends(userId: Long): ArrayList<UserDTO>
//    @Select(
//            """
//        SELECT id, name, email FROM users WHERE name LIKE CONCAT('%', #{searchStr}, '%')
//        """
//    )
//    fun findBySearchStr(searchStr: String): ArrayList<UserDTO>
}