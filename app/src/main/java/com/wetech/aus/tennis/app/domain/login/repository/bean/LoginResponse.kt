package com.wetech.aus.tennis.app.domain.login.repository.bean

data class LoginResponse(
    val token: String,
    val userInfo: UserInfo
) {
    data class UserInfo(
        val avatar: Any,
        val createBy: Int,
        val createDate: String,
        val experienceValue: Any,
        val id: Int,
        val level: Any,
        val password: Any,
        val phone: String,
        val phonePrefix: String,
        val starts: Any,
        val status: Any,
        val updateBy: Any,
        val updateDate: Any,
        val userName: String
    )
}