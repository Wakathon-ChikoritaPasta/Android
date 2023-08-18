package com.chikorita.gamagochi.base

import com.chikorita.gamagochi.model.LadybugLocationRequest
import com.chikorita.gamagochi.model.LevelRankingResponse
import com.chikorita.gamagochi.model.MajorAllResponse
import com.chikorita.gamagochi.model.MajorName
import com.chikorita.gamagochi.model.MissionResponse
import com.chikorita.gamagochi.model.RegisterInfo
import com.chikorita.gamagochi.model.SuccessMissionResponse
import com.chikorita.gamagochi.model.UserUpdateRequest
import com.chikorita.gamagochi.model.UserUpdateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    // 회원가입
    @POST("/api/auth/join")
    suspend fun postJoin(@Body request: RegisterInfo) : Response<BaseResponse>

    // 카카오 로그인



    @POST("/api/ladybug")
    suspend fun postLadyBugLocation(@Body request : LadybugLocationRequest) : Response<SuccessMissionResponse>

    @GET("/api/ladybug/detail")
    suspend fun getLadybugDetail(@Query("userId") userId: Long) : Response<LevelRankingResponse>

    @GET("/api/level/ranking")
    suspend fun getLevelRanking() : Response<LevelRankingResponse>

    @GET("/api/major/all")
    suspend fun getMajorAll() : Response<MajorAllResponse>

    @GET("/api/level/names")
    suspend fun getMajorNames() : Response<MajorName>

    @GET("/api/mission")
    suspend fun getMission() : Response<MissionResponse>

    @PATCH("/api/user/update")
    suspend fun patchUserUpdate(@Body userUpdateRequest: UserUpdateRequest) : Response<UserUpdateResponse>

}