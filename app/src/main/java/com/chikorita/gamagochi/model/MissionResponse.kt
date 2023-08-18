package com.chikorita.gamagochi.model

import com.chikorita.gamagochi.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class MissionResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("missionList") val result: ArrayList<MissionList>
    )
data class MissionList(
    val latitude : Int = 0,
    val longitude : Int = 0,
    val missionId : Int = 0,
    val missionName : String = ""
)