package com.wetech.aus.tennis.app.domain

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:08
 * @Desc: 路由表
 * @GitHub：https://github.com/pokerfaceCmy
 */
object RoutePath {
    const val MainActivity = "/main/MainActivity"

    object Login {
        const val SmsLoginActivity = "/login/SmsLoginActivity"
        const val VerificationCodeActivity = "/login/VerificationCodeActivity"
        const val PasswordLoginActivity = "/login/PasswordLoginActivity"
        const val RetrievePswActivity = "/login/RetrievePswActivity"
        const val PrefixActivity = "/login/PrefixActivity"

    }

    object Home

    object Courts

    object Booking

    object Profile{
        const val EditProfileActivity = "/profile/EditProfileActivity"
    }

    object Club {
        const val ClubDetailActivity = "/club/ClubDetailActivity"
        const val ClubBookingActivity = "/club/ClubBookingActivity"
    }
}