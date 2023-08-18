package com.chikorita.gamagochi.view.ranking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.adapter.SchoolRankingRVAdapter
import com.chikorita.gamagochi.base.BaseBindingFragment
import com.chikorita.gamagochi.databinding.FragmentSchoolRankingBinding
import com.chikorita.gamagochi.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SchoolRankingFragment(id: Int): BaseBindingFragment<FragmentSchoolRankingBinding>(R.layout.fragment_school_ranking) {

    val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        CoroutineScope(Dispatchers.IO).launch {
            setDataView()
        }
    }

    private suspend fun setDataView() {
        withContext(Dispatchers.Main) {
        with(viewModel){
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
            }
        }
    }
}