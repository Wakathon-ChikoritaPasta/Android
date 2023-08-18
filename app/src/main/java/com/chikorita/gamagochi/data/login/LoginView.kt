package com.chikorita.gamagochi.data.login

interface LoginView {
    fun onPostKakaoSDKSuccess(response: KakaoSDKResponse)
    fun onPostKakaoSDKFailure(message: String)
}