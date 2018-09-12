package com.line.fukuokabserver.Auth

interface Auth {
    fun verifyIdToken(id_token: String?): String?
}