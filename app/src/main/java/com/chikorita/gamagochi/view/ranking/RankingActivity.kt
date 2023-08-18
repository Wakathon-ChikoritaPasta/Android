package com.chikorita.gamagochi.view.ranking

import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.databinding.ActivityRankingBinding
import com.chikorita.gamagochi.view.ranking.adapter.RankingVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class RankingActivity: BaseActivity<ActivityRankingBinding>(ActivityRankingBinding::inflate) {
    override fun initView() {

        setViewPager()
    }

    private fun setViewPager() {
        val information = arrayListOf("학교 랭킹", "학과 랭킹")

        val adapter = RankingVPAdapter(this)
        binding.addViewpagerVp.adapter = adapter

        TabLayoutMediator(binding.rankingTab, binding.addViewpagerVp){
                tab, position->
            tab.text= information[position]  //포지션에 따른 텍스트
        }.attach()  //탭레이아웃과 뷰페이저를 붙여주는 기능

        // 뷰페이저 스와이프 막기
//        binding.addViewpagerVp.isUserInputEnabled = false
    }


}