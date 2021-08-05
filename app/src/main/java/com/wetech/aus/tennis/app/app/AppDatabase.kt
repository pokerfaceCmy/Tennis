package com.wetech.aus.tennis.app.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfo
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfoDao

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/30 15:25
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

@Database(
    entities = [
        UserInfo::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}