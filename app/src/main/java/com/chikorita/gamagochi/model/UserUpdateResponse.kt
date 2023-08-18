package com.chikorita.gamagochi.model

import com.google.gson.annotations.SerializedName

data class UserUpdateResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("result") val result: Succeed
)
data class Succeed(
    @SerializedName("succeed") val succeed: String

)

