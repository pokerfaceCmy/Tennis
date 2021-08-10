package com.wetech.aus.tennis.app.domain.club.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceByDayResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/10 17:12
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class PlaceAdapter : BaseQuickAdapter<UsablePlaceByDayResponse.Data,BaseViewHolder>(R.layout.item_booking_place) {
    override fun convert(holder: BaseViewHolder, item: UsablePlaceByDayResponse.Data) {
    }
}