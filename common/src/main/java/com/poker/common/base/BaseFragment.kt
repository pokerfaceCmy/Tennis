package com.poker.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.XPopupCallback
import com.poker.common.IUIActionEventObserver
import com.poker.common.widget.dialog.SimpleLoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import java.lang.reflect.ParameterizedType

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/15 10:23
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

abstract class BaseFragment<VB : ViewBinding> : Fragment(), IUIActionEventObserver {
    protected lateinit var binding: VB

    override val lifecycleSupportedScope: CoroutineScope
        get() = lifecycleScope

    override val mContext: Context?
        get() = requireActivity()

    override val mLifecycleOwner: LifecycleOwner
        get() = this
    private lateinit var job: Job

    private val loadingDialog: BasePopupView? by lazy {
        XPopup.Builder(mContext)
            .dismissOnTouchOutside(false)
            .dismissOnBackPressed(true)
            .setPopupCallback(object : XPopupCallback {
                override fun onCreated(popupView: BasePopupView?) {}

                override fun beforeShow(popupView: BasePopupView?) {}

                override fun onShow(popupView: BasePopupView?) {}

                override fun onDismiss(popupView: BasePopupView?) {
                    //如果已完成任务,正常销毁dialog
                    if (this@BaseFragment::job.isInitialized) {
                        if (job.isCompleted) {
                            return
                        } else {//取消任务,弹出提示
                            job.cancel()
                            showToast("已取消")
                        }
                    }
                }

                override fun beforeDismiss(popupView: BasePopupView?) {
                }

                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return false
                }

                override fun onKeyBoardStateChanged(popupView: BasePopupView?, height: Int) {}

                override fun onDrag(
                    popupView: BasePopupView?,
                    value: Int,
                    percent: Float,
                    upOrLeft: Boolean
                ) {
                }

            })
            .asCustom(mContext?.let { SimpleLoadingDialog(it) })

    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz0 = type.actualTypeArguments[0] as Class<VB>
        val method = clazz0.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, inflater) as VB
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()

    override fun showLoading() {
        loadingDialog?.takeIf { it.isDismiss }?.show()
    }

    override fun showLoading(job: Job?) {
        if (job != null) {
            this.job = job
        }
        showLoading()
    }

    override fun dismissLoading() {
        loadingDialog?.takeIf { it.isShow }?.smartDismiss()
    }

    override fun showToast(msg: String) {
        ToastUtils.showLong(msg)
    }

}