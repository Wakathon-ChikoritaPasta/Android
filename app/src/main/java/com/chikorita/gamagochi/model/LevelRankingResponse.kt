package com.chikorita.gamagochi.model

import com.chikorita.gamagochi.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class LevelRankingResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("result") val result: RankingResult
    )

data class RankingResult(
    val rankingList: List<RankingList>
)
data class RankingList(
    val experience: Int = 0,
    val ladybugType: String = "",
    val nickName: String = "",
    val rank: Int = 0
)