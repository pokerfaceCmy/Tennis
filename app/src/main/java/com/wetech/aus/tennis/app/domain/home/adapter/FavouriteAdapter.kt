package com.wetech.aus.tennis.app.domain.home.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 11:34
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class FavouriteAdapter :
    BaseQuickAdapter<ClubListResponse.Data?, BaseViewHolder>(R.layout.item_main_favourite) {

    override fun convert(holder: BaseViewHolder, item: ClubListResponse.Data?) {
        holder.setText(R.id.tvName, item?.name)
        holder.setText(R.id.tvIntroduction, item?.clubDesc)
        holder.getView<ShapeableImageView>(R.id.img).load(item?.cover)
    }
}