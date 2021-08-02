package com.wetech.aus.tennis.app.domain.home.adapter

import androidx.appcompat.widget.AppCompatImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/21 11:50
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RecommendAdapter :
    BaseQuickAdapter<ClubListResponse.Data?, BaseViewHolder>(R.layout.item_main_recommend) {

    override fun convert(holder: BaseViewHolder, item: ClubListResponse.Data?) {
        val img = holder.getView<ShapeableImageView>(R.id.img)
        img.load(item?.cover)

        holder.setText(R.id.tvName, item?.name)
        holder.setText(R.id.tvContent, item?.clubDesc)

        val ivLike = holder.getView<AppCompatImageView>(R.id.ivLike)
        ivLike.load(if (item?.enjoy == 1) R.drawable.ic_favorites_h else R.drawable.ic_favorites_n)
    }

}