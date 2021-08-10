package com.wetech.aus.tennis.app.domain.club.adapter

import android.graphics.Color
import android.view.View.GONE
import android.view.View.VISIBLE
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceTimeResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 17:53
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class TimeAdapter :
    BaseQuickAdapter<UsablePlaceTimeResponse.Data, BaseViewHolder>(R.layout.item_booking_time) {

    override fun convert(holder: BaseViewHolder, item: UsablePlaceTimeResponse.Data) {
        val tvTime = holder.getView<MaterialTextView>(R.id.tvTime)
        tvTime.text = item.time
        val imgCheck = holder.getView<ShapeableImageView>(R.id.imgCheck)
        if (!item.isCheck) {
            tvTime.apply {
                setTextColor(Color.parseColor("#737373"))
            }
            imgCheck.visibility = GONE
        } else {
            tvTime.apply {
                setTextColor(Color.parseColor("#0E009E"))
            }
            imgCheck.visibility = VISIBLE
        }
    }

}