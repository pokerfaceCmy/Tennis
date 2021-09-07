package com.wetech.aus.tennis.app.domain.booking.adapter

import android.graphics.Color
import android.widget.TextView
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
        holder.setText(R.id.tvPlaceName, item?.placeInfo?.name)
        holder.setText(
            R.id.tvTime,
            item?.orderInfo?.orderDate + " " + item?.orderInfo?.startSlot + "-" + item?.orderInfo?.endSlot
        )

        val tvOrderStatus = holder.getView<TextView>(R.id.tvOrderStatus)
        when (item?.orderInfo?.orderStatus) {
            0 -> {
                tvOrderStatus.text = "取消预约"
                tvOrderStatus.setTextColor(Color.parseColor("#737373"))
            }
            1 -> {
                tvOrderStatus.text = "预约成功"
                tvOrderStatus.setTextColor(Color.parseColor("#0E009E"))
            }
            2 -> {
                tvOrderStatus.text = "已使用"
                tvOrderStatus.setTextColor(Color.parseColor("#737373"))
            }
            3 -> {
                tvOrderStatus.text = "已过期"
                tvOrderStatus.setTextColor(Color.parseColor("#737373"))
            }
            4 -> {
                tvOrderStatus.text = "待支付"
                tvOrderStatus.setTextColor(Color.parseColor("#737373"))
            }
        }
    }
}