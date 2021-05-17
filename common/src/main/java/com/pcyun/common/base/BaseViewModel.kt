package com.pcyun.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcyun.common.*
import com.pcyun.common.callback.BaseRequestCallback
import com.pcyun.common.callback.RequestCallback
import com.pcyun.common.exception.BaseHttpException
import com.pcyun.common.exception.LocalException
import com.pcyun.common.exception.ServerException
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.*

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/12 17:32
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseViewModel : ViewModel(), IViewModelActionEvent {

    override fun showSuccessSnackBar(msg: String) {
    }

    override fun showErrorSnackBar(msg: String) {

    }

    override val lifecycleSupportedScope: CoroutineScope
        get() = viewModelScope

    override val showLoadingEventLD = MutableLiveData<ShowLoadingEvent>()

    override val dismissLoadingEventLD = MutableLiveData<DismissLoadingEvent>()

    override val showToastEventLD = MutableLiveData<ShowToastEvent>()

    override val showLoadingWithoutJobEventLD = MutableLiveData<ShowLoadingWithoutJobEvent>()

    override val loginAndFinishLD = MutableLiveData<LoginAndFinishEvent>()


    protected val data: WeakHashMap<String, Any> = WeakHashMap()


    fun <Data> enqueue(
        apiFun: suspend () -> Data,
        showLoading: Boolean = true,
        showErrorMsg: Boolean = true,
        callbackFun: (RequestCallback<Data>.() -> Unit)? = null
    ): Job {
        return lifecycleSupportedScope.launch(Dispatchers.Main) {
            val callback = if (callbackFun == null) null else RequestCallback<Data>().apply {
                callbackFun.invoke(this)
            }
            try {
                if (showLoading) {
                    showLoading(coroutineContext[Job])
                }
                callback?.onStart?.invoke()
                val response: Data?
                try {
                    response = apiFun.invoke()
                } catch (ex: Exception) {
                    handleException(showErrorMsg, ex, callback)
                    return@launch
                }
                onGetResponse(callback, response)
            } finally {
                try {
                    callback?.onFinally?.invoke()
                } finally {
                    if (showLoading) {
                        dismissLoading()
                        data.clear()
                    }
                }
            }
        }
    }


    private suspend fun <Data> onGetResponse(callback: RequestCallback<Data>?, httpData: Data?) {
        callback?.let {
            withContext(NonCancellable) {
                callback.onSuccess?.let {
                    withContext(Dispatchers.Main) {
                        it.invoke(httpData)
                    }
                }
                callback.onSuccessIO?.let {
                    withContext(Dispatchers.IO) {
                        it.invoke(httpData)
                    }
                }
            }
        }
    }

    private fun handleException(
        showErrorMsg: Boolean,
        ex: Exception,
        callback: BaseRequestCallback?
    ) {
        callback?.let {
            if (ex is CancellationException) {
                callback.onCancelled?.invoke()
                return
            }
            val exception = generateException(showErrorMsg, ex)
            callback.onFailed?.invoke(exception)
        }
    }

    private fun generateException(showErrorMsg: Boolean, ex: Exception): BaseHttpException {
        ex.printStackTrace()
        return when (ex) {
            is CancellationException -> {
                BaseHttpException(404, "请求已取消")
            }
            is ServerException -> {
                if (showErrorMsg) {
                    showToast(ex.errMsg)
                }
                BaseHttpException(ex.errCode, ex.errMsg)
            }
            is LocalException -> {
                if (ex.errCode == 403) {
                    Timber.i("前往登录页面")
                    loginAndFinish()
                }
                BaseHttpException(ex.errCode, ex.errMsg)
            }
            else -> {
                BaseHttpException(400, "未知错误")
            }
        }

    }

}
