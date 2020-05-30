package com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.databinding.FragmentAboutMeBinding

class AboutMeFragmentTab : ViewBindingFragment<FragmentAboutMeBinding>() {
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAboutMeBinding {
        return FragmentAboutMeBinding.inflate(inflater, container, false)
    }

    fun setProfessional(professional: Professional) {
        binding.professional = professional
    }
}