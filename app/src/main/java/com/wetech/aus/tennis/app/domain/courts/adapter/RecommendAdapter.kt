package com.wetech.aus.tennis.app.domain.courts.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import com.wetech.aus.tennis.widget.TrapezoidImgView

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 11:46
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RecommendAdapter :
    BaseQuickAdapter<ClubListResponse.Data, BaseViewHolder>(R.layout.item_courts_recommend) {

    override fun convert(holder: BaseViewHolder, item: ClubListResponse.Data) {
        holder.setText(R.id.tvName, item.name)
        holder.setText(R.id.tvBriefIntroduction, item.clubDesc)
        holder.setText(R.id.tvPhone, item.tel)

        val img = holder.getView<TrapezoidImgView>(R.id.img)
        img.load(item.cover)
    }
}