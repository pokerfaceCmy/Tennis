package com.wetech.aus.tennis.app.domian.booking.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/20 10:54
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class BookingAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_booking) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val img = holder.getView<ShapeableImageView>(R.id.img)
        img.load("https://avatars.githubusercontent.com/u/43669001?v=4")

        holder.setText(R.id.tvName,"Tennis Club Tennis Club Tennis Club Tennis Club")

    }
}