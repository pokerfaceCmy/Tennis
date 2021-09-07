package com.wetech.aus.tennis.app.domain.home.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.bean.DataWrapper
import com.wetech.aus.tennis.app.domain.home.repository.HomeClient
import com.wetech.aus.tennis.app.domain.home.repository.bean.BannerResponse
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/2 9:44
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeClient: HomeClient
) : BaseViewModel() {
    val bannerLD = MutableLiveData<BannerResponse?>()
    val queryRecommendClubListLD = MutableLiveData<ClubListResponse?>()
    val queryFavouritesClubListLD = MutableLiveData<ClubListResponse?>()
    val likeClubLD = MutableLiveData<DataWrapper<*>?>()
    val searchClubLD = MutableLiveData<ClubListResponse?>()

    fun getBanner() {
        enqueue({ homeClient.getBanner() }) {
            onSuccess {
                bannerLD.value = it
            }
        }
    }

    fun queryRecommendClubList(clubListRequest: ClubListRequest) {
        enqueue({ homeClient.queryClubList(clubListRequest) }) {
            onSuccess {
                queryRecommendClubListLD.value = it
            }
        }
    }

    fun queryFavouritesClubList(clubListRequest: ClubListRequest) {
        enqueue({ homeClient.queryClubList(clubListRequest) }) {
            onSuccess {
                queryFavouritesClubListLD.value = it
            }
        }
    }

    fun searchClub(keyword: String) {
        val clubListRequest = ClubListRequest(
            searchName = keyword
        )
        enqueue({ homeClient.queryClubList(clubListRequest) }) {
            onSuccess {
                searchClubLD.value = it
            }
        }
    }

    fun likeClub(clubId: Long?, type: String) {
        enqueue(
            { clubId?.let { homeClient.likeClub(it, type) } },
            showLoading = false,
            showErrorMsg = false
        ) {
            onSuccess {
                likeClubLD.value = it
            }
        }
    }
}