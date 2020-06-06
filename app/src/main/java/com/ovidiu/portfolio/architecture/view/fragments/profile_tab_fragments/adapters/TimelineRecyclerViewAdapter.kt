package com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters

import android.os.Parcelable
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.view.fragments.ProfileFragmentDirections
import com.ovidiu.portfolio.databinding.RecyclerViewItemTimelineBinding
import com.ovidiu.portfolio.support.getLayoutBinding
import kotlinx.android.parcel.Parcelize

class TimelineRecyclerViewAdapter :
    RecyclerView.Adapter<TimelineRecyclerViewAdapter.TimelineItemViewHolder>() {
    private var timelineList: List<TimelineItemDataModel>? = null

    fun updateList(experienceList: List<TimelineItemDataModel>) {
        this.timelineList = experienceList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineItemViewHolder {
        val mBinding =
            parent.getLayoutBinding(R.layout.recycler_view_item_timeline) as RecyclerViewItemTimelineBinding

        return TimelineItemViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: TimelineItemViewHolder, position: Int) {
        val timeline = timelineList?.get(position)

        holder.bind(
            timeline!!,
            position == 0,
            position == timelineList?.size?.minus(1)
        )

        holder.mBinding.root.setOnClickListener {
            it.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToTimelineExtendedFragment(timeline))
        }

        holder.mBinding.executePendingBindings()
    }

    override fun getItemCount() = timelineList?.size ?: 0

    class TimelineItemViewHolder(val mBinding: RecyclerViewItemTimelineBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(dataModel: TimelineItemDataModel, isFirstItem: Boolean, isLastItem: Boolean) {
            mBinding.dataModel = dataModel
            mBinding.isFirstItem = isFirstItem
            mBinding.isLastItem = isLastItem
        }
    }

    @Keep
    @Parcelize
    data class TimelineItemDataModel(
        val title: String,
        val subtitle: String,
        val description: String,
        val rangeYears: String
    ): Parcelable
}