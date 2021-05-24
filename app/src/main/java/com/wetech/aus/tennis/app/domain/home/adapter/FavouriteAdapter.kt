package com.wetech.aus.tennis.app.domain.home.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 11:34
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class FavouriteAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main_favourite) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvName,"XX Tennis club")
        holder.setText(R.id.tvIntroduction,"Club Introduction")
        holder.getView<ShapeableImageView>(R.id.img).load("https://images.uiiiuiii.com/wp-content/uploads/2021/05/i-banner-ww0511-1-02.jpg")
    }
}