package com.wetech.aus.tennis.app.domain.booking.repository.bean

import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 15:00
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class BookingListResponse(
    val hasNextPage : Boolean,
    val list: List<Data>?
) {
    data class Data(
        val orderInfo: OrderInfo?,
        val clubInfo: ClubListResponse.Data?,
        val placeInfo: PlaceInfo?
    ) {
        data class OrderInfo(
            val clubId: Int?,
            val endSlot: String?,
            val id: Int?,
            val orderDate: String?,
            val orderDesc: String?,
            val orderNo: String?,
            val orderStatus: Int?,
            val placeId: Int?,
            val startSlot: String?,
            val userId: Int?
        )

        data class PlaceInfo(
            val clubId: Long?,
            val cover: String?,
            val id: Int?,
            val intro: String?,
            val label: String?,
            val name: String?,
        )
    }
}
