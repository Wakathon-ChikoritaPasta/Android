package com.chikorita.gamagochi.model

data class LadybugLocationRequest (
    val latitude: Double,
    val longitude: Double,
    val missionIdList: List<Int>
    )

data class SuccessMissionResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SuccessMissionResult
)

data class SuccessMissionResult(
    val successMission: List<Int>
)