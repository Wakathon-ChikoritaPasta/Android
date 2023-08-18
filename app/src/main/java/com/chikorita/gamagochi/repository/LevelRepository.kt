package com.chikorita.gamagochi.repository

import android.app.Application
import com.chikorita.gamagochi.base.ApiInterface
import com.chikorita.gamagochi.base.ApplicationClass

class LevelRepository {
    private val mainClient = ApplicationClass.bRetrofit.create(ApiInterface::class.java)

    suspend fun getLevelRanking() = mainClient.getLevelRanking()

    suspend fun getMajorAll() = mainClient.getMajorAll()



}