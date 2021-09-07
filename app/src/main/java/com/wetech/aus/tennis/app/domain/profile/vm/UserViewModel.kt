package com.wetech.aus.tennis.app.domain.profile.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.bean.DataWrapper
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfo
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfoDao
import com.wetech.aus.tennis.app.domain.login.repository.remote.LoginClient
import com.wetech.aus.tennis.app.domain.profile.repository.OssTokenVOResp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/31 15:37
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginClient: LoginClient,
    private val userInfoDao: UserInfoDao
) : BaseViewModel() {

    val getOssTokenVOLD = MutableLiveData<OssTokenVOResp?>()

    val updateUserAvatarLD = MutableLiveData<DataWrapper<*>>()

    val getUserInfoLD = MutableLiveData<UserInfo?>()

    val updateUserInfoLD = MutableLiveData<DataWrapper<*>>()


    fun getOssTokenVO(path: String) {
        enqueue({ loginClient.getOssTokenVO() }) {
            onSuccess {
                it?.imgPath = path
                getOssTokenVOLD.value = it
            }
        }

    }

    fun updateUserAvatar(avatar: String) {
        enqueue({ loginClient.updateUserAvatar(avatar) }) {
            onSuccess {
                updateUserAvatarLD.value = it
            }
        }
    }


    fun getUserInfo() {
        enqueue({ loginClient.getUserInfo() }) {
            onSuccess {
                getUserInfoLD.value = it

                viewModelScope.launch(Dispatchers.IO) {
                    saveUserInfo(it)
                }
            }
        }
    }

    fun updateUserInfo(userInfo: UserInfo?) {
        enqueue({ loginClient.updateUserInfo(userInfo) }) {
            onSuccess {
                updateUserInfoLD.value = it

                viewModelScope.launch(Dispatchers.IO) {
                    saveUserInfo(userInfo)
                }
            }
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