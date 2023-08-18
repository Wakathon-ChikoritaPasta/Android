package com.chikorita.gamagochi.view.ranking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseBindingFragment
import com.chikorita.gamagochi.databinding.FragmentSchoolRankingBinding
import com.chikorita.gamagochi.view.ranking.adapter.RankingRVAdapter
import com.chikorita.gamagochi.viewModel.MainViewModel

class SchoolRankingFragment(id: Int): BaseBindingFragment<FragmentSchoolRankingBinding>(R.layout.fragment_school_ranking) {

    val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupAdapter()
    }

    fun setupAdapter() {
        binding.exampleRecyclerView.adapter = RankingRVAdapter(viewModel)
    }
}