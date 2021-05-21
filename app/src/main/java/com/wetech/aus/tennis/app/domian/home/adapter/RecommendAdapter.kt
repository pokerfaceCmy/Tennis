package com.wetech.aus.tennis.app.domian.home.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/21 11:50
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RecommendAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main_recommend) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val img = holder.getView<ShapeableImageView>(R.id.img)
        img.load("https://avatars.githubusercontent.com/u/43669001?v=4")

        holder.setText(R.id.tvName,"Tennis Club Tennis Club Tennis Club Tennis Club")
    }
}