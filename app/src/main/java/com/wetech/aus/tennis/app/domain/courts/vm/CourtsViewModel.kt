package com.wetech.aus.tennis.app.domain.courts.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.domain.home.repository.HomeClient
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/4 12:10
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class CourtsViewModel @Inject constructor(
    private val homeClient: HomeClient
) : BaseViewModel() {
    val queryRecommendClubListLD = MutableLiveData<ClubListResponse?>()

    fun queryRecommendClubList(clubListRequest: ClubListRequest) {
        enqueue({ homeClient.queryClubList(clubListRequest) }, showLoading = false) {
            onSuccess {
                queryRecommendClubListLD.value = it
            }
        }
    }
}