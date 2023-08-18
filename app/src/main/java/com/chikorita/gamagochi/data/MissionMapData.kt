package com.chikorita.gamagochi.data

data class MissionMapData (
    var missionId: Int,
    var missionName: String? = null, // 미션 이름
    var latitude: Double, // 위도
    var longitude: Double // 경도
    )