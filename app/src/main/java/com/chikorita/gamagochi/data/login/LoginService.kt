package com.chikorita.gamagochi.data.login

import android.util.Log
import com.chikorita.gamagochi.base.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginView) {
    val loginRetrofitInterface = ApplicationClass.bRetrofit.create(LoginRetrofitInterface::class.java)
    fun tryPostKakaoSDK(token: String) {
        loginRetrofitInterface.postKakaoSDK(token).enqueue(object : Callback<KakaoSDKResponse> {

            override fun onResponse(call: Call<KakaoSDKResponse>, response: Response<KakaoSDKResponse>) {
                when (response.code()) {
                    200 -> view.onPostKakaoSDKSuccess(response.body() as KakaoSDKResponse)
                    else -> view.onPostKakaoSDKFailure("기타 오류")
                }

            }

            override fun onFailure(call: Call<KakaoSDKResponse>, t: Throwable) {
                Log.d("KakaoLogin", "onFailure")
                view.onPostKakaoSDKFailure(t.message ?: "통신 오류")
            }
        })
    }
}