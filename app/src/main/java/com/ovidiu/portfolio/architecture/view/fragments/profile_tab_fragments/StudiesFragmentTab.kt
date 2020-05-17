package com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters.TimelineRecyclerViewAdapter

class StudiesFragmentTab: Fragment(R.layout.fragment_studies) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TimelineRecyclerViewAdapter()
        recyclerView.adapter = adapter
    }
}