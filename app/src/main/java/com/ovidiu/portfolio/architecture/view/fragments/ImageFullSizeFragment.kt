package com.ovidiu.portfolio.architecture.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.databinding.FragmentImageFullSizeBinding
import com.ovidiu.portfolio.support.srcDrawable

class ImageFullSizeFragment :
    ViewBindingFragment<FragmentImageFullSizeBinding>() {

    private val args: ImageFullSizeFragmentArgs by navArgs()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageFullSizeBinding {
        return FragmentImageFullSizeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        binding.imageTitle.text = args.imageTitle
        binding.imageView.srcDrawable(R.drawable.ovidiu)

        requireActivity().window.statusBarColor = resources.getColor(android.R.color.black, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        requireActivity().window.statusBarColor = resources.getColor(R.color.primaryDarkColor, null)
    }
}