package com.chikorita.gamagochi.repository

import android.app.Application
import com.chikorita.gamagochi.base.ApiInterface
import com.chikorita.gamagochi.base.ApplicationClass

class LevelRepository {
    private val mainClient = ApplicationClass.sRetrofit.create(ApiInterface::class.java)

    suspend fun getLevelRanking() = mainClient.getLevelRanking()


}