package com.wetech.aus.tennis.app.domain.club.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.textview.MaterialTextView
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.booking.repository.bean.DaysResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 16:16
 * @Desc: =
 * @GitHub：https://github.com/pokerfaceCmy
 */
class DateAdapter :
    BaseQuickAdapter<DaysResponse.Data, BaseViewHolder>(R.layout.item_booking_date) {
    override fun convert(holder: BaseViewHolder, item: DaysResponse.Data) {
        item.day?.let { day -> holder.setText(R.id.tvDay, day.toString()) }
        item.week?.let { week -> holder.setText(R.id.tvWeek, week) }

        val tvDay = holder.getView<MaterialTextView>(R.id.tvDay)
        if (item.isCheck) {
            tvDay.setBackgroundResource(R.drawable.bg_item_booking_tvday_select)
            tvDay.setTextColor(Color.WHITE)
        } else {
            tvDay.setBackgroundResource(0)
            tvDay.setTextColor(Color.parseColor("#232323"))
        }
    }
}