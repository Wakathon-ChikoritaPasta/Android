package com.chikorita.gamagochi.data.mission

import android.util.Log
import com.chikorita.gamagochi.base.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MissionService(val view: MissionView) {
    val retrofitInterface = ApplicationClass.bRetrofit.create(MissionRetrofitInterface::class.java)
    fun tryGetMissionMap() {
        retrofitInterface.getMissionMap().enqueue(object : Callback<MissionMapResponse> {

            override fun onResponse(call: Call<MissionMapResponse>, response: Response<MissionMapResponse>) {
                when (response.code()) {
                    200 -> view.onGetMissionSuccess(response.body() as MissionMapResponse)
                    else -> view.onGetMissionFailure("기타 오류")
                }
            }

            override fun onFailure(call: Call<MissionMapResponse>, t: Throwable) {
                Log.d("GetMission", "onFailure")
                view.onGetMissionFailure(t.message ?: "통신 오류")
            }
        })
    }
}