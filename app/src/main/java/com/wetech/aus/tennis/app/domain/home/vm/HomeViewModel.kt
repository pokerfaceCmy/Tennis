package com.wetech.aus.tennis.app.domain.home.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.domain.home.repository.HomeClient
import com.wetech.aus.tennis.app.domain.home.repository.bean.BannerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/2 9:44
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeClient: HomeClient
) : BaseViewModel() {
    val bannerLD = MutableLiveData<BannerResponse?>()

    fun getBanner() {
        enqueue({ homeClient.getBanner() }) {
            onSuccess {
                bannerLD.value = it
            }
        }
    }

}