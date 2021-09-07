package com.poker.oss

import com.alibaba.sdk.android.oss.ClientException
import com.alibaba.sdk.android.oss.OSSClient
import com.alibaba.sdk.android.oss.ServiceException
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.poker.oss.callback.UploadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/31 10:35
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class OssManager : OssService {

    fun simpleUpload(
        path: String,
        ossClient: OSSClient? = null,
        lifecycleSupportedScope: CoroutineScope,
        callbackFun: (UploadCallback.() -> Unit)? = null
    ): Job {
        return lifecycleSupportedScope.launch {
            val callback = if (callbackFun == null) null else UploadCallback().apply {
                callbackFun.invoke(this)
            }
            try {
                callback?.onStart?.invoke()
                uploadAliStorage(path, ossClient, callback)
            } finally {
                callback?.onFinally?.invoke()
            }
        }
    }


    private fun uploadAliStorage(path: String, ossClient: OSSClient?, callback: UploadCallback?) {
        val objKey = "avatar/" + System.currentTimeMillis().toString()
        val putObjReq = PutObjectRequest("tennis-image-test", objKey, path)

        try {
            putObjReq.setProgressCallback { request, currentSize, totalSize ->
                callback?.onLoad?.invoke(Progress(currentSize, totalSize))
            }
//            val request = ossClient?.putObject(putObjReq)
            ossClient?.asyncPutObject(putObjReq,
                object : OSSCompletedCallback<PutObjectRequest, PutObjectResult>{
                    override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult?) {
                        callback?.onSuccess?.invoke(objKey)
                    }

                    override fun onFailure(
                        request: PutObjectRequest?,
                        clientException: ClientException?,
                        serviceException: ServiceException?
                    ) {
                    }

                })

//            val task: OSSAsyncTask<PutObjectResult>? =
//            ossClient?.asyncPutObject(putObjReq,
//                object : OSSCompletedCallback<PutObjectRequest?, PutObjectResult?> {
//                    override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult?) {
//                        callback?.onSuccess?.invoke(objKey)
//                        Timber.d("onSuccess")
//
//                    }
//
//                    override fun onFailure(
//                        request: PutObjectRequest?,
//                        clientException: ClientException?,
//                        serviceException: ServiceException?
//                    ) {
//                        callback?.onFailed?.invoke(clientException as Exception)
//                    }
//
//                })
        } catch (e: ClientException) {
            // 客户端异常，例如网络异常等
            e.printStackTrace()

        } catch (e: ServiceException) {
            // 服务端异常
            e.printStackTrace()
        }
    }
}