package com.wetech.aus.tennis.app.domain.courts.adapter

import androidx.appcompat.widget.AppCompatImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.button.MaterialButton
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import com.wetech.aus.tennis.widget.TrapezoidImgView

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/24 11:46
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RecommendAdapter :
    BaseQuickAdapter<ClubListResponse.Data, BaseViewHolder>(R.layout.item_courts_recommend) {

    override fun convert(holder: BaseViewHolder, item: ClubListResponse.Data) {
        holder.setText(R.id.tvName, item.name)
        holder.setText(R.id.tvBriefIntroduction, item.clubDesc)
        holder.setText(R.id.tvPhone, "Tel:" + item.tel)
        holder.setText(R.id.tvDistance, item.distance.toString() + "m")
        holder.getView<MaterialButton>(R.id.materialButton).text =
            if (item.level == 2) "Nearest to me" else "Excellent environment"

        val img = holder.getView<TrapezoidImgView>(R.id.img)
        img.load(item.cover)

        val ivLike = holder.getView<AppCompatImageView>(R.id.ivLike)
        ivLike.load(if (item.enjoy == 1) R.drawable.ic_favorites_h else R.drawable.ic_favorites_n)

    }
}