package com.wetech.aus.tennis.app.domain.login.repository.bean

import androidx.room.*

data class LoginResponse(
    val token: String?,
    val userInfo: UserInfo?
)

@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey
    val id: Long,
    var avatar: String?,
    @ColumnInfo(name = "experience_value")
    val experienceValue: Int?,
    val level: String?,
    val password: String?,
    var phone: String?,
    @ColumnInfo(name = "phone_prefix")
    val phonePrefix: String?,
    val starts: String?,
    val status: Int?,
    @ColumnInfo(name = "user_name")
    val userName: String?,
    @ColumnInfo(name = "first_name")
    var firstName: String?,
    @ColumnInfo(name = "last_name")
    var lastName: String?,
    var gender: String?,
    var birthday: String?
)

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userInfo: UserInfo)

    @Query("SELECT * FROM user_info")
    fun query(): UserInfo?

    @Update
    fun update(userInfo: UserInfo?)
}