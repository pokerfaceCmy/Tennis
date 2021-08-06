package com.wetech.aus.tennis.app.domain.club.adapter

import android.widget.TextView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 15:21
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class CoachAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_club_coach) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<ShapeableImageView>(R.id.imgAvatar)
            .load("https://avatars.githubusercontent.com/u/43669001?v=4")

        holder.getView<TextView>(R.id.tvCoachName).text = "Coach 1"
    }
}