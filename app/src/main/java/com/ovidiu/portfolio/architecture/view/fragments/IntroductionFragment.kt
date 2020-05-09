package com.ovidiu.portfolio.architecture.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ovidiu.portfolio.databinding.FragmentIntroductionBinding

class IntroductionFragment : ViewBindingFragment<FragmentIntroductionBinding>() {
    override fun getViewBinding(inflater: LayoutInflater,container: ViewGroup?): FragmentIntroductionBinding {
        return FragmentIntroductionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnKnowMoreAboutMe.setOnClickListener (
            Navigation.createNavigateOnClickListener(IntroductionFragmentDirections.actionIntroductionFragmentToProfileFragment())
        )
    }
}
