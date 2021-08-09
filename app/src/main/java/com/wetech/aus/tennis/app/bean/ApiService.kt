package com.wetech.aus.tennis.app.bean

import com.wetech.aus.tennis.app.domain.home.repository.bean.BannerResponse
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import com.wetech.aus.tennis.app.domain.login.repository.bean.LoginRequest
import com.wetech.aus.tennis.app.domain.login.repository.bean.LoginResponse
import com.wetech.aus.tennis.app.domain.login.repository.bean.PrefixResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:58
 * @Desc: retrofit接口类
 * @GitHub：https://github.com/pokerfaceCmy
 */
interface ApiService {
    /**
     * 发送验证码
     */
    @GET("/system/sendSms")
    suspend fun sendSms(
        @Query("phone") phone: String,
        @Query("prefix") prefix: String
    ): DataWrapper<*>

    /**
     * 获取国家对应的区号
     */
    @GET("/system/getCountryMobilePrefix")
    suspend fun getCountryMobilePrefix(): PrefixResponse?

    /**
     * 校验是否存在此用户
     * @param phone 手机号
     * @param prefix 前缀
     *
     * @return Boolean 是否存在
     */
    @GET("/system/checkHasUser")
    suspend fun checkHasUser(
        @Query("phone") phone: String,
        @Query("prefix") prefix: String
    ): Boolean

    /**
     * 登录
     */
    @POST("/system/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse?

    /* --------------------------------- */

    /**
     * 轮播图
     */
    @GET("/image/queryPollingImage")
    suspend fun getBanner(): BannerResponse?

    /**
     * 俱乐部列表
     */
    @POST("/club/queryClubList")
    suspend fun queryClubList(@Body clubListRequest: ClubListRequest): ClubListResponse?

    /**
     * 喜欢&取消喜欢 俱乐部
     * @param clubId 俱乐部Id
     * @param type 1，喜欢，2取消喜欢
     */
    @GET("/club/enjoyClub")
    suspend fun likeClub(
        @Query("clubId") clubId: Long,
        @Query("type") type: String
    ): DataWrapper<*>
}