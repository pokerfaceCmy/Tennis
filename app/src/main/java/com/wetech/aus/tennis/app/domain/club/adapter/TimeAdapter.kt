package com.wetech.aus.tennis.app.domain.club.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceTimeResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 17:53
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class TimeAdapter :
    BaseQuickAdapter<UsablePlaceTimeResponse.Data?, BaseViewHolder>(R.layout.item_booking_time) {
    override fun convert(holder: BaseViewHolder, item: UsablePlaceTimeResponse.Data?) {

    }
}