package com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters.TimelineRecyclerViewAdapter
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters.TimelineRecyclerViewAdapter.TimelineItemDataModel
import com.ovidiu.portfolio.databinding.FragmentExperienceBinding

class ExperienceFragmentTab: ViewBindingFragment<FragmentExperienceBinding>() {
    private val adapter = TimelineRecyclerViewAdapter()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentExperienceBinding {
        return FragmentExperienceBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
    }

    fun setExperienceList(experienceList: List<TimelineItemDataModel>) {
        adapter.updateList(experienceList)
    }
}