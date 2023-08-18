package com.chikorita.gamagochi.data.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginRetrofitInterface {
    // SDK 카카오 로그인
    @POST("api/auth/kakao-login")
    fun postKakaoSDK(
        @Query("accessToken") accessToken: String?
    ) : Call<KakaoSDKResponse>
}