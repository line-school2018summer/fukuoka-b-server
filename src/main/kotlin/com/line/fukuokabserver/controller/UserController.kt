package com.line.fukuokabserver.controller

import com.line.fukuokabserver.Auth.Auth
import com.line.fukuokabserver.dto.UserDTO
import com.line.fukuokabserver.service.UserDAO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*



@RestController
class UserController(private val userService: UserDAO, private val auth: Auth) {
    @GetMapping(
            value = ["/test"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun hello(): String{
        return "{\"greeting\": \"Hello World!\"}"
    }

    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun test(@RequestHeader(value = "Token", required = true) token: String): UserDTO{
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Invalod Token")
        return UserDTO(1, "me", token, "", "")
    }

    @GetMapping(
            value = ["/user/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUser(@RequestHeader(value = "Token", required = true) token: String, @PathVariable("id" ) id: Long): UserDTO {
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Invalod Token")
        return userService.getUser(id)
    }

    @GetMapping(
            value = ["/user/userId/{userId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserByUserId(@RequestHeader(value = "Token", required = true) token: String, @PathVariable("userId" ) userId: String): UserDTO {
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Invalod Token")
        return userService.getUserByUserId(userId)
    }

    @GetMapping(
            value = ["/user/id/{mail}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserByMail(@RequestHeader(value = "Token", required = true) token: String, @PathVariable("mail") mail:String): UserDTO {
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Invalod Token")
        return userService.getUserByMail(mail)
    }

    @PostMapping(
            value = ["/user/friend/add"]
//            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addFriend(@RequestHeader(value = "Token", required = true) token: String, @RequestBody request: PostAddFriends) {
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Invalod Token")
//        TODOcheck already added friends
        userService.addFriend(request.userId, request.friendId)
    }

    @GetMapping(
            value = ["/user/{id}/friends"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getFriend(@RequestHeader(value = "Token", required = true) token: String, @PathVariable("id") id: Long): List<UserDTO> {
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Invalod Token")
        return userService.getFriendList(id)
    }

    @PostMapping(
            value = ["user/{id}/update"]
    )
    fun updateUser(@RequestBody request: PostUpdateUser){
        //Cliend側のRESTのHashmapのデータ型の都合でrequest.idがString型になっているのでLong型に戻しています
        val user = userService.getUser(request.id.toLong())
        user.name = request.name
        user.hitokoto = request.hitokoto
        return userService.updateUser(user)
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