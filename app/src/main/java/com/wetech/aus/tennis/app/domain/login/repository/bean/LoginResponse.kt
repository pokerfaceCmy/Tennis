package com.wetech.aus.tennis.app.domain.login.repository.bean

import androidx.room.*

data class LoginResponse(
    val token: String?,
    val userInfo: UserInfo?
)

@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey val id: Long,
    val avatar: String?,
    @ColumnInfo(name = "experience_value") val experienceValue: Int?,
    val level: String?,
    val password: String?,
    val phone: String?,
    @ColumnInfo(name = "phone_prefix") val phonePrefix: String?,
    val starts: String?,
    val status: Int?,
    @ColumnInfo(name = "user_name") val userName: String?
)

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userInfo: UserInfo)

    @Query("SELECT * FROM user_info")
    fun query(): UserInfo?
}