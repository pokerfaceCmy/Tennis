package com.wetech.aus.tennis.app.domain.club.adapter

import android.graphics.Color
import android.widget.TextView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceByDayResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/10 17:12
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class PlaceAdapter :
    BaseQuickAdapter<UsablePlaceByDayResponse.Data, BaseViewHolder>(R.layout.item_booking_place) {
    override fun convert(holder: BaseViewHolder, item: UsablePlaceByDayResponse.Data) {
        val img = holder.getView<ShapeableImageView>(R.id.img)
        img.load(item.cover)
        val tvPlace = holder.getView<TextView>(R.id.tvPlace)
        tvPlace.text = item.name
        val cardView = holder.getView<MaterialCardView>(R.id.cardView)

        if (item.isCheck) {
            cardView.apply {
                strokeColor = Color.parseColor("#0E009E")
                strokeWidth = 2
            }
            tvPlace.setTextColor(Color.parseColor("#0E009E"))
        } else {
            cardView.apply {
                strokeColor = Color.parseColor("#FFFFFF")
                strokeWidth = 0
            }
            tvPlace.setTextColor(Color.parseColor("#737373"))
        }
    }
}