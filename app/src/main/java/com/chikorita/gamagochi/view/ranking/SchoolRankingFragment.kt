package com.chikorita.gamagochi.view.ranking

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.adapter.MajorRankingRVAdapter
import com.chikorita.gamagochi.adapter.SchoolRankingRVAdapter
import com.chikorita.gamagochi.base.BaseBindingFragment
import com.chikorita.gamagochi.databinding.FragmentSchoolRankingBinding
import com.chikorita.gamagochi.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SchoolRankingFragment(fragmentId: Int): BaseBindingFragment<FragmentSchoolRankingBinding>(R.layout.fragment_school_ranking) {

    val viewModel: MainViewModel by viewModels()
    val fragmentId = fragmentId
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val logoImageView = binding.mudangLogo
        val rotateAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotate_logo_360)
        logoImageView.startAnimation(rotateAnimation)

        CoroutineScope(Dispatchers.IO).launch {
            setDataView()
        }
    }

    private suspend fun setDataView() {
        withContext(Dispatchers.Main) {
        with(viewModel){
            if(fragmentId == 0) {
                user.observe(viewLifecycleOwner) {


                    val userAdapter =
                        activity?.let { it1 -> SchoolRankingRVAdapter(it, it1) }
                    binding.exampleRecyclerView.apply {
                        adapter = userAdapter
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                    userAdapter?.setOnItemClickListener(object :
                        SchoolRankingRVAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                        }
                    })

                }
                getLevelRanking()

            }else {


                major.observe(viewLifecycleOwner) {


                    val majorAdapter =
                        activity?.let { it1 -> MajorRankingRVAdapter(it, it1) }
                    binding.exampleRecyclerView.apply {
                        adapter = majorAdapter
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                    majorAdapter?.setOnItemClickListener(object :
                        MajorRankingRVAdapter.OnItemClickListener {

                        override fun onClick(position: Int, rank: Int) {
                        }
                    })

                }
                getMajorAll()

            }
        }
        }
    }
}