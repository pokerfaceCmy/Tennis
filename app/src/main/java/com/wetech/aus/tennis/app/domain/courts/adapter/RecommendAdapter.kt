package com.wetech.aus.tennis.app.domain.courts.adapter

import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.RoutePath

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 11:46
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RecommendAdapter : BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_courts_recommend) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.setOnClickListener {
            ARouter.getInstance()
                .build(RoutePath.Companion.Club.ClubDetailActivity)
                .navigation()
        }
    }
}