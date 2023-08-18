package com.chikorita.gamagochi.viewModel

import androidx.lifecycle.ViewModel
import com.chikorita.gamagochi.model.MajorRanker
import com.chikorita.gamagochi.model.SchoolRanker

class MainViewModel : ViewModel(){
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



}