package com.chikorita.gamagochi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chikorita.gamagochi.model.LevelRankingResponse
import com.chikorita.gamagochi.model.MajorRanker
import com.chikorita.gamagochi.model.RankingList
import com.chikorita.gamagochi.model.SchoolRanker
import com.chikorita.gamagochi.repository.LevelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    private val levelRepository = LevelRepository()

    var MajorRankerArray = arrayListOf(
    MajorRanker(1, "소프트웨어", 450000),
    MajorRanker(2, "인공지능", 390000),
    MajorRanker(3, "컴퓨터공학", 380000)
    )
    var SchoolRankerArray = arrayListOf(
    SchoolRanker(16, "로건", "무당신", 3400),
    SchoolRanker(17, "마라", "무당짱", 2300),
    SchoolRanker(18, "코코아", "무당이", 1700)
    )

    private val _user = MutableLiveData<ArrayList<RankingList>>()
    val user : LiveData<ArrayList<RankingList>>
        get() = _user

    fun getLevelRanking() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = levelRepository.getLevelRanking()
            //Log.d("sns_add", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _user.value = (response.body()?.result as ArrayList<RankingList>)
                }
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }




}