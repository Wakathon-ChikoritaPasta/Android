package com.chikorita.gamagochi.data.login

import com.chikorita.gamagochi.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class KakaoSDKResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    val result: KakaoSDKResult
)
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