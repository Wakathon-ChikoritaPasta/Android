package com.chikorita.gamagochi.data.mission

import com.chikorita.gamagochi.base.BaseResponse


data class MissionMapResponse(
    val result: MissionMapResult
) : BaseResponse()
data class MissionMapResult(
    val missionList: ArrayList<MissionMapData>
)
data class MissionMapData(
    var missionId: Int,
    var missionName: String? = null, // 미션 이름
    var latitude: Double, // 위도
    var longitude: Double // 경도
)