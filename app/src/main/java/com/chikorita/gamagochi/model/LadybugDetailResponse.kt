package com.chikorita.gamagochi.model

import com.chikorita.gamagochi.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class LadybugDetailResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("result") val result: LadybugDetailResult
    )
data class LadybugDetailResult(
    val levelInfo: LevelInfo,
    val majorRank: List<MajorRank>,
    val majorType : String = "ARTIFICIAL_INTELLIGENCE",
    val schoolRank : List<SchoolRank>,
    val symbol: String = "Bronze"
)

data class LevelInfo(
    val experience: Int = 0,
    val ladybugType : String = "EGG",
    val level : Int = 0,

)

data class MajorRank(
    val experience: Int = 0,
    val majorType : String = "ARTIFICIAL_INTELLIGENCE",
    val rank : Int = 0,
)

data class SchoolRank(
    val experience: Int = 0, // 경험치
    val ladybugType : String = "EGG", // 레벨
    val nickname : String = "string", // 닉네임
    val rank : Int = 0 // 순위
)

