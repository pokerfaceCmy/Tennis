package com.wetech.aus.tennis.app.domain.booking.adapter

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingListResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/20 10:54
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class BookingAdapter :
    BaseQuickAdapter<BookingListResponse.Data?, BaseViewHolder>(R.layout.item_booking) {

    override fun convert(holder: BaseViewHolder, item: BookingListResponse.Data?) {
        val ivClub = holder.getView<ShapeableImageView>(R.id.ivClub)
        ivClub.load(item?.clubInfo?.cover)

        holder.setText(R.id.tvName, item?.clubInfo?.name)
    }
}