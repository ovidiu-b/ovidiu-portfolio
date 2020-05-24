package com.ovidiu.portfolio.architecture.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.viewmodel.IntroductionViewModel
import com.ovidiu.portfolio.databinding.FragmentIntroductionBinding
import com.ovidiu.portfolio.support.circleDrawable
import javax.inject.Inject

class IntroductionFragment : ViewBindingFragment<FragmentIntroductionBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<IntroductionViewModel> { viewModelFactory }

    override fun getViewBinding(inflater: LayoutInflater,container: ViewGroup?): FragmentIntroductionBinding {
        return FragmentIntroductionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageIntroduction.circleDrawable(R.drawable.ovidiu)

        binding.btnKnowMoreAboutMe.setOnClickListener (
            Navigation.createNavigateOnClickListener(IntroductionFragmentDirections.actionIntroductionFragmentToProfileFragment())
        )
    }
}
