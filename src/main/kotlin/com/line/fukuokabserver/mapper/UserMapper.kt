package com.line.fukuokabserver.mapper

import com.line.fukuokabserver.dto.UserDTO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select(
            """
        SELECT id, name, user_id, mail FROM users WHERE id=#{userId}
        """
    )
    fun findByUserId(userId: Long): UserDTO

//    @Select(
//            """
//        SELECT id, name, email FROM users WHERE name LIKE CONCAT('%', #{searchStr}, '%')
//        """
//    )
//    fun findBySearchStr(searchStr: String): ArrayList<UserDTO>
}