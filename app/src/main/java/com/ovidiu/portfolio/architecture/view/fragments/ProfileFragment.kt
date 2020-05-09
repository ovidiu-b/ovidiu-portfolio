package com.ovidiu.portfolio.architecture.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.AboutMeFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.ExperienceFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.StudiesFragmentTab
import com.ovidiu.portfolio.databinding.FragmentProfileBinding


class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>() {
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar = binding.topAppBar
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val viewPagerAdapter = ProfileFragmentPageAdapter(activity?.supportFragmentManager!!)

        topAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        Glide
            .with(requireContext())
            .load(R.drawable.ovidiu_2)
            .apply(RequestOptions().circleCrop())
            .into(binding.imageProfile)

        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = viewPagerAdapter.count - 1
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = "Sobre mí"
        tabLayout.getTabAt(1)?.text = "Experiencia"
        tabLayout.getTabAt(2)?.text = "Estudios"
    }
}

private class ProfileFragmentPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AboutMeFragmentTab()
            1 -> ExperienceFragmentTab()
            else -> StudiesFragmentTab()
        }
    }

    override fun getCount() = 3
}
