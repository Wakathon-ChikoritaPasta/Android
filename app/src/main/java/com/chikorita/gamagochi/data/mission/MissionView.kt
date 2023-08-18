package com.chikorita.gamagochi.data.mission

interface MissionView {
    fun onGetMissionSuccess(response: MissionMapResponse)
    fun onGetMissionFailure(message: String)
}