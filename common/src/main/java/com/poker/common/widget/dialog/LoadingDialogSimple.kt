package com.poker.common.widget.dialog

import android.content.Context
import com.lxj.xpopup.core.CenterPopupView
import com.poker.common.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/12 15:03
 * @Desc: 默认的Loading Dialog
 * @GitHub：https://github.com/pokerfaceCmy
 */
class LoadingDialogSimple(context: Context) : CenterPopupView(context) {
    override fun getImplLayoutId(): Int {
        return R.layout.dialog_loading
    }

}