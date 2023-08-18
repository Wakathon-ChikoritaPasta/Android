package com.chikorita.gamagochi.data.register

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface RegisterRetrofitInterface {

    @PATCH("api/user/update")
    fun setUserInfo(
        @Body request: SetUserInfoRequest
    ): Call<SetUserInfoResponse>

    @GET("api/major/names")
    fun getMajorList(): Call<GetMajorListResult>
}