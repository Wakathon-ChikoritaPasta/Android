package com.chikorita.gamagochi.data.register

data class SetUserInfoRequest(
    val userId: Int,
    val newUsername: String,
    val newMajor: String
)

data class SetUserInfoResponse(
    val result: SetUserInfoResult
)

data class SetUserInfoResult(
    val succeed: Boolean
)

data class GetMajorListResponse(
    val result: GetMajorListResult
)

data class GetMajorListResult(
    val majorList: ArrayList<String>
)
