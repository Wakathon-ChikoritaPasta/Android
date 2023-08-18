package com.chikorita.gamagochi.data.mission

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface MissionRetrofitInterface {
    // 미션 받아오기
    @GET("api/mission")
    fun getMissionMap (
        //
    ) : Call<MissionMapResponse>
}