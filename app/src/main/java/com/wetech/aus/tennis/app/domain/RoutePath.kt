package com.wetech.aus.tennis.app.domain

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:08
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RoutePath {
    companion object {
        const val MainActivity = "/main/MainActivity"

        object Login{
            const val VerificationCodeActivity = "/login/VerificationCodeActivity"
            const val PasswordLoginActivity = "/login/PasswordLoginActivity"
            const val RetrievePswActivity = "/login/RetrievePswActivity"
        }
        object Home

        object Courts

        object Booking

        object Profile

        object Club{
            const val ClubDetailActivity = "/club/ClubDetailActivity"
            const val ClubBookingActivity = "/club/ClubBookingActivity"
        }
    }
}