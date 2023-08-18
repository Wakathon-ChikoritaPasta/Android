package com.chikorita.gamagochi.data.login

import com.chikorita.gamagochi.base.BaseResponse

data class KakaoSDKResponse(
    val result: KakaoSDKResult
) : BaseResponse()
data class KakaoSDKResult(
    val userId: Int,
    val newUser: Boolean,
    val accessToken: String,
    val refreshToken: String
)

data class SdkTokenBody(
    val kakaoAccessToken: String,
    val kakaoRefreshToken: String
)