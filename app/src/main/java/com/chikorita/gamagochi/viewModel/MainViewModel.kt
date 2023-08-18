package com.chikorita.gamagochi.viewModel

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chikorita.gamagochi.model.LadybugLocationRequest
import com.chikorita.gamagochi.model.LadybugDetailResult
import com.chikorita.gamagochi.model.LevelRankingResponse
import com.chikorita.gamagochi.model.MajorInfo
import com.chikorita.gamagochi.model.MajorRank
import com.chikorita.gamagochi.model.MajorRanker
import com.chikorita.gamagochi.model.RankingList
import com.chikorita.gamagochi.model.SchoolRanker
import com.chikorita.gamagochi.repository.LadybugRepository
import com.chikorita.gamagochi.repository.LevelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Error

class MainViewModel : ViewModel(){
    private val levelRepository = LevelRepository()
    private val ladybugRepository = LadybugRepository()


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

    private val _ladybug = MutableLiveData<LadybugDetailResult>()
    val ladybug : LiveData<LadybugDetailResult>
        get() = _ladybug

    fun getLadybugDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ladybugRepository.getLadybugDetail()
                if (response.isSuccessful && response.body() != null) {
                    viewModelScope.launch(Dispatchers.Main) {
                        _ladybug.value = (response.body()?.result as LadybugDetailResult)
                    }
                } else {
                    // handle error
                }
            } catch (e: NetworkErrorException) {
                // handle network timeout exception
            }
        }
    }

//    fun getLadybugDetail() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = ladybugRepository.getLadybugDetail()
//            Log.d("뭐냐",response.body().toString())
//            if (response.isSuccessful && response.body() != null) {
//                viewModelScope.launch(Dispatchers.Main) {
//                    _ladybug.value = (response.body()?.result as LadybugDetailResult)
//                    Log.d("뭘까",_ladybug.value.toString())
//                }
//            }
//            else {
//                //response.body()?.message?.let { Log.d("error", it) }
//            }
//        }
//    }

    fun getLevelRanking() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = levelRepository.getLevelRanking()
            Log.d("뭐냐",response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _user.value = (response.body()?.result?.rankingList as ArrayList<RankingList>)
                    Log.d("뭘까",_user.value.toString())

                }
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _major = MutableLiveData<ArrayList<MajorInfo>>()
    val major : LiveData<ArrayList<MajorInfo>>
        get() = _major

    fun getMajorAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = levelRepository.getMajorAll()
            Log.d("뭐냐2",response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _major.value = (response.body()?.majorList as ArrayList<MajorInfo>)
                    Log.d("뭘까2",_major.value.toString())

                }
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _mission = MutableLiveData<List<Long>>()
    val mission : LiveData<List<Long>>
        get() = _mission


    fun postLocation(latitude : Double, longitude : Double, mission : List<Long>) {
        val ladybugLocationRequest = LadybugLocationRequest(latitude, longitude, mission)
        Log.d("Location_error", ladybugLocationRequest.toString())

        viewModelScope.launch(Dispatchers.IO) {
            val response = ladybugRepository.postLocation(ladybugLocationRequest)
            Log.d("Location_error", response.toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _mission.value = (response.body()나?.result as List<Long>)
                    Log.d("뭘까냐4", _mission.value.toString())

                }
            } else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }




}