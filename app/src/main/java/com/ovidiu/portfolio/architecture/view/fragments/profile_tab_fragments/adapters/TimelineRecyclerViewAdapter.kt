package com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.support.getLayoutBinding
import com.ovidiu.portfolio.databinding.RecyclerViewItemTimelineBinding

class TimelineRecyclerViewAdapter: RecyclerView.Adapter<TimelineRecyclerViewAdapter.ExperienceItemViewHolder>() {
    private var experienceList: List<String>? = List(10) { "" }

    /*fun updateListOfWorkOrders(experienceList: List<String>) {
        this.experienceList = experienceList

        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceItemViewHolder {
        val mBinding= parent.getLayoutBinding(R.layout.recycler_view_item_timeline) as RecyclerViewItemTimelineBinding

        return ExperienceItemViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ExperienceItemViewHolder, position: Int) {
        //val experience = experienceList?.get(position)

        holder.bind(position == 0,position == experienceList?.size?.minus(1))

        holder.mBinding.executePendingBindings()
    }

    override fun getItemCount() = experienceList?.size ?: 0

    class ExperienceItemViewHolder(val mBinding: RecyclerViewItemTimelineBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(isFirstItem:Boolean, isLastItem: Boolean) {
            mBinding.isFirstItem = isFirstItem
            mBinding.isLastItem = isLastItem
        }
    }
}