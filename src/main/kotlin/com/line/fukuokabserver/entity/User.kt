package com.line.fukuokabserver.entity

import com.line.fukuokabserver.dto.UserDTO

class User(
        var Id: Long,
        var userId: String,
        var userName: String,
        var email: String
) {
    constructor(dto: UserDTO): this(dto.Id, dto.userId, dto.userName, dto.email)
}