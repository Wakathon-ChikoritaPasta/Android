package com.chikorita.gamagochi.model

data class MajorAllResponse(
    val majorList : ArrayList<MajorInfo>
)

data class MajorInfo(
    val name : String,
    val totalExperience : String
)
