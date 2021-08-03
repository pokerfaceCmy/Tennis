package com.wetech.aus.tennis.app.domain.home.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/2 9:47
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class BannerResponse(
    val list: List<Data>?
) {
    data class Data(
        val imageUrl: String
    )
}

data class ClubListRequest(
    val pageNum: Int,
    val pageSize: Int = 10,
)

data class ClubListResponse(
    val list: List<Data>?
) {
    data class Data(
        val clubDesc: String?,
        val cover: String?,
        val createBy: Long?,
        val createDate: String?,
        val distance: Double?,
        val enjoy: Int?,
        val id: Long?,
        val latitude: Double?,
        val level: Int?,
        val longitude: Double?,
        val name: String?,
        val recommend: Int?,
        val star: Int?,
        val tel: String?,
        val updateBy: Long?,
        val updateDate: String?
    )
}