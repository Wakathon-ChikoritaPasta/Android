package com.chikorita.gamagochi.data.register

import android.util.Log
import com.chikorita.gamagochi.base.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterService(val view: RegisterView) {
    val registerRetrofitInterface = ApplicationClass.bRetrofit.create(RegisterRetrofitInterface::class.java)
    fun trySetUserInfo(userId: Int, newUsername: String, newMajor: String) {
        registerRetrofitInterface.setUserInfo(SetUserInfoRequest(userId, newUsername, newMajor)).enqueue(object :
            Callback<SetUserInfoResponse> {
            override fun onResponse(call: Call<SetUserInfoResponse>, response: Response<SetUserInfoResponse>) {
                when (response.code()) {
                    200 -> view.onSetUserInfoSuccess(response.body() as SetUserInfoResponse)
                    else -> view.onSetUserInfoFailure(response.toString())
                }
            }

            override fun onFailure(call: Call<SetUserInfoResponse>, t: Throwable) {
                Log.d("SetUserInfo", "onFailure")
                view.onSetUserInfoFailure(t.message ?: "통신 오류")
            }
        })
    }
    fun tryGetMajorList() {
        registerRetrofitInterface.getMajorList().enqueue(object :
            Callback<GetMajorListResult> {
            override fun onResponse(call: Call<GetMajorListResult>, response: Response<GetMajorListResult>) {
                when (response.code()) {
                    200 -> view.onGetMajorListSuccess(response.body() as GetMajorListResult)
                    else -> view.onGetMajorListFailure(response.toString())
                }
            }

            override fun onFailure(call: Call<GetMajorListResult>, t: Throwable) {
                Log.d("GetMajorList", "onFailure")
                view.onGetMajorListFailure(t.message ?: "통신 오류")
            }
        })
    }
}