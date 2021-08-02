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
