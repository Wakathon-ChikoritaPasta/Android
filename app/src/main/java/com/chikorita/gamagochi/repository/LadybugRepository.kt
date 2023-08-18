package com.chikorita.gamagochi.repository

import com.chikorita.gamagochi.base.ApiInterface
import com.chikorita.gamagochi.base.ApplicationClass
import com.chikorita.gamagochi.model.LadybugLocationRequest

import com.chikorita.gamagochi.base.ApplicationClass.Companion.USER_ID
import com.chikorita.gamagochi.base.ApplicationClass.Companion.sSharedPreferences

class LadybugRepository {
    private val mainClient = ApplicationClass.bRetrofit.create(ApiInterface::class.java)

    suspend fun postLocation(ladybugLocationRequest : LadybugLocationRequest) = mainClient.postLadyBugLocation(ladybugLocationRequest, 17)

    suspend fun getLadybugDetail() = mainClient.getLadybugDetail(17)
//    suspend fun getLadybugDetail() = mainClient.getLadybugDetail(sSharedPreferences.getLong(USER_ID, 0))
}