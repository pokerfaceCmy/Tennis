package com.wetech.aus.tennis.app.domain.login.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.poker.common.exception.BaseHttpException
import com.wetech.aus.tennis.app.domain.login.repository.bean.PrefixResponse
import com.wetech.aus.tennis.app.domain.login.repository.remote.LoginClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 10:02
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginClient: LoginClient
) : BaseViewModel() {

    val sendSmsSuccessLD = MutableLiveData<Any>()
    val sendSmsFailedLD = MutableLiveData<BaseHttpException>()

    val getPrefixLD = MutableLiveData<PrefixResponse?>()

    fun sendSms() {
        enqueue({ loginClient.sendSms() }) {
            onSuccess {

            }
            onFailed {
                sendSmsFailedLD.value = it
            }
        }
    }

    fun getCountryMobilePrefix() {
        enqueue({ loginClient.getCountryMobilePrefix()}) {
            onSuccess {
                getPrefixLD.value = it
            }
        }
    }
}