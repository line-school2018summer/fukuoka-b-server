package com.line.fukuokabserver.entity

import com.line.fukuokabserver.dto.UserDTO

class User(
        var Id: Long,
        var name: String,
        var user_id: String,
        var email: String
) {
    constructor(dto: UserDTO): this(dto.Id, dto.name, dto.user_id, dto.email)
}