package com.ovidiu.portfolio.architecture.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.databinding.FragmentTimelineExtendedBinding

class TimelineExtendedFragment: ViewBindingFragment<FragmentTimelineExtendedBinding>() {
    private val args: TimelineExtendedFragmentArgs by navArgs()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTimelineExtendedBinding {
        return FragmentTimelineExtendedBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        binding.timeline = args.timeline
    }
}