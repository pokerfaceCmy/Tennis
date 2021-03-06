package com.wetech.aus.tennis.app.domain.login.vm

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.poker.common.base.BaseViewModel
import com.poker.common.exception.BaseHttpException
import com.poker.common.interceptor.TokenInterceptor.Companion.TOKEN
import com.wetech.aus.tennis.app.bean.DataWrapper
import com.wetech.aus.tennis.app.domain.login.repository.bean.*
import com.wetech.aus.tennis.app.domain.login.repository.remote.LoginClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 10:02
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginClient: LoginClient,
    private val dataStore: DataStore<Preferences>,
    private val userInfoDao: UserInfoDao
) : BaseViewModel() {

    val sendSmsSuccessLD = MutableLiveData<DataWrapper<*>>()
    val sendSmsFailedLD = MutableLiveData<BaseHttpException>()
    val getPrefixLD = MutableLiveData<PrefixResponse?>()
    val checkHasUserLD = MutableLiveData<Boolean>()
    val loginLd = MutableLiveData<LoginResponse>()

    fun sendSms(phone: String, prefix: String) {
        enqueue({ loginClient.sendSms(phone, prefix) }) {
            onSuccess {

            }
            onFailed {
                sendSmsFailedLD.value = it
            }
        }
    }

    fun getCountryMobilePrefix() {
        enqueue({ loginClient.getCountryMobilePrefix() }) {
            onSuccess {
                getPrefixLD.value = it
            }
        }
    }

    fun checkHasUser(phone: String, prefix: String) {
        enqueue({ loginClient.checkHasUser(phone, prefix) }) {
            onSuccess { checkHasUserLD.value = it }
        }
    }

    fun login(loginRequest: LoginRequest) {
        enqueue({ loginClient.login(loginRequest) }) {
            onSuccess { loginResponse ->
                loginLd.value = loginResponse

                viewModelScope.launch(Dispatchers.IO) {
                    saveToken(loginResponse?.token)
                    saveUserInfo(loginResponse?.userInfo)
                }
            }
        }
    }

    /**
     * 将token存入dataStore中
     */
    private suspend fun saveToken(token: String?) {
        token?.let {
            dataStore.edit {
                val key = preferencesKey<String>(name = TOKEN)
                it[key] = token
            }
            //异步预加载数据,等要用的时候可以同步取出
            dataStore.data.first()
        }
    }

    /**
     * 将用户信息保存到数据库
     */
    private fun saveUserInfo(userinfo: UserInfo?) {
        userinfo?.let {
            userInfoDao.insert(userinfo)
        }
    }

}