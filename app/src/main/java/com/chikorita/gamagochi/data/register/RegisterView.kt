package com.chikorita.gamagochi.data.register

interface RegisterView {
    fun onSetUserInfoSuccess(response: SetUserInfoResponse)
    fun onSetUserInfoFailure(message: String)
}