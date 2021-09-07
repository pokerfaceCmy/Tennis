package com.poker.oss.callback

import com.poker.oss.Progress

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/31 10:41
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class UploadCallback(
    internal var onStart: (() -> Unit)? = null,
    internal var onCancelled: (() -> Unit)? = null,
    internal var onFailed: ((Exception) -> Unit)? = null,
    internal var onLoad: ((Progress) -> Unit)? = null,
    internal var onSuccess: ((String) -> Unit)? = null,
    internal var onFinally: (() -> Unit)? = null
) {
    fun onStart(block: () -> Unit) {
        this.onStart = block
    }

    fun onCancelled(block: () -> Unit) {
        this.onCancelled = block
    }

    /**
     * 当网络请求失败时会调用此方法，在 onFinally 被调用之前执行
     */
    fun onFailed(block: (Exception) -> Unit) {
        this.onFailed = block
    }

    fun onLoad(block: (Progress) -> Unit) {
        this.onLoad = block
    }


    fun onSuccess(block: (String) -> Unit) {
        this.onSuccess = block
    }


    /**
     * 在网络请求结束之后（不管请求成功与否）且隐藏 Loading 之前执行
     */
    fun onFinally(block: () -> Unit) {
        this.onFinally = block
    }
}