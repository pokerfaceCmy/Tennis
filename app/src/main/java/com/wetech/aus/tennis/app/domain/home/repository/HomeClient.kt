package com.wetech.aus.tennis.app.domain.home.repository

import com.wetech.aus.tennis.app.bean.ApiService
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/2 9:45
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class HomeClient @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getBanner() = apiService.getBanner()

    suspend fun queryClubList(clubListRequest: ClubListRequest) =
        apiService.queryClubList(clubListRequest)

    suspend fun likeClub(clubId: Long, type: String) = apiService.likeClub(clubId, type)
}