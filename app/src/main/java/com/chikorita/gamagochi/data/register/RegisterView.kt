package com.chikorita.gamagochi.data.register

interface RegisterView {
    fun onSetUserInfoSuccess(response: SetUserInfoResponse)
    fun onSetUserInfoFailure(message: String)
    fun onGetMajorListSuccess(response: GetMajorListResult)
    fun onGetMajorListFailure(message: String)
}