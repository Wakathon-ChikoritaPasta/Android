package com.chikorita.gamagochi.repository

import com.chikorita.gamagochi.base.ApiInterface
import com.chikorita.gamagochi.base.ApplicationClass
import com.chikorita.gamagochi.model.LadybugLocationRequest

class LadybugRepository {
    private val mainClient = ApplicationClass.bRetrofit.create(ApiInterface::class.java)

    suspend fun postLocation(ladybugLocationRequest : LadybugLocationRequest) = mainClient.postLadyBugLocation(ladybugLocationRequest)

}