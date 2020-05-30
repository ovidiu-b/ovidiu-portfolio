package com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters.TimelineRecyclerViewAdapter
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters.TimelineRecyclerViewAdapter.TimelineItemDataModel
import com.ovidiu.portfolio.databinding.FragmentStudyBinding

class StudyFragmentTab: ViewBindingFragment<FragmentStudyBinding>() {
    private val adapter = TimelineRecyclerViewAdapter()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentStudyBinding {
        return FragmentStudyBinding.inflate(inflater, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
    }

    fun setStudyList(experienceList: List<TimelineItemDataModel>) {
        adapter.updateList(experienceList)
    }
}