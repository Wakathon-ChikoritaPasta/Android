package com.chikorita.gamagochi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chikorita.gamagochi.databinding.ComponentRankingUserBinding
import com.chikorita.gamagochi.model.RankingList
import com.chikorita.gamagochi.model.SchoolRanker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolRankingRVAdapter (private val dataList: ArrayList<RankingList>,val mContext : Context): RecyclerView.Adapter<SchoolRankingRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ComponentRankingUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: RankingList) {
            binding.rank = data.rank
            binding.exp = data.experience.toLong()
            binding.symbol = data.ladybugType
            binding.nickName = data.nickName

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ComponentRankingUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(position)
        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }
}
