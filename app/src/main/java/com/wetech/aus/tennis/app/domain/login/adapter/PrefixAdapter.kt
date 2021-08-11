package com.wetech.aus.tennis.app.domain.login.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.login.repository.bean.PrefixResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/11 15:11
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class PrefixAdapter :
    BaseQuickAdapter<PrefixResponse.Data, BaseViewHolder>(R.layout.item_smslogin_prefix) {
    override fun convert(holder: BaseViewHolder, item: PrefixResponse.Data) {
        holder.setText(R.id.tvCode, item.code)
        holder.setText(R.id.tvName, item.name)
    }
}