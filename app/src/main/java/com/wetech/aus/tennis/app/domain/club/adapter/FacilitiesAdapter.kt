package com.wetech.aus.tennis.app.domain.club.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 15:16
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class FacilitiesAdapter : BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_club_facilities) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvFacilities,"Car park")

        holder.getView<ShapeableImageView>(R.id.img).load("https://www.stripeuk.com/wp-content/uploads/2018/11/shutterstock_540175210-BW.jpg")
    }
}