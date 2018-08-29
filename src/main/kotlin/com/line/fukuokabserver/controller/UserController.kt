package com.line.fukuokabserver.controller

import com.line.fukuokabserver.dto.UserDTO
import com.line.fukuokabserver.service.UserDAO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

data class UserListResponse(
        var id: Long,
        var name: String,
        var email: String
)

data class PostSearchRequest(
        val search_str: String
)

@RestController
class UserController(private val userService: UserDAO) {
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun hello(): String{
        return "{\"greeting\": \"Hello World!\"}"
    }

    @GetMapping(
            value = ["/user/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUser(@PathVariable("id" ) id: Long): UserDTO {
        return userService.getUser(id)
    }

    @GetMapping(
            value = ["/user/userId/{userId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserByUserId(@PathVariable("userId" ) userId: String): UserDTO {
        return userService.getUserByUserId(userId)
    }

    @GetMapping(
            value = ["/user/id/{mail}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserByMail(@PathVariable("mail") mail:String): UserDTO {
        return userService.getUserByMail(mail)
    }
//    @PostMapping(
//            value = ["/user/search"],
//            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
//    )
//    fun getList(@RequestBody request: PostSearchRequest): Map<String, List<UserListResponse>> {
//        val userList: ArrayList<UserList> = userService.findUsersList(request.search_str)
//        return mapOf("results" to userList.map {
//            UserListResponse(
//                    id = it.id,
//                    name = it.name,
//                    email = it.email
//            )
//        })
//    }

}