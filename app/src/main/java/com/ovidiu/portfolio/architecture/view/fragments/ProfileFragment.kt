package com.ovidiu.portfolio.architecture.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ovidiu.portfolio.MainApplication
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.AboutMeFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.ExperienceFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.StudiesFragmentTab
import com.ovidiu.portfolio.architecture.viewmodel.ProfessionalViewModel
import com.ovidiu.portfolio.databinding.FragmentProfileBinding
import com.ovidiu.portfolio.support.circleDrawable
import javax.inject.Inject

class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProfessionalViewModel> { viewModelFactory }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as MainApplication).applicationComponent.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, viewModel.getMessage().toString(), Toast.LENGTH_LONG).show()

        val topAppBar = binding.topAppBar
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val viewPagerAdapter = ProfileFragmentPageAdapter(activity?.supportFragmentManager!!)

        if(topAppBar.menu is MenuBuilder) {
            (topAppBar.menu as MenuBuilder).setOptionalIconsVisible(true)
        }

        topAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        binding.imageProfile.circleDrawable(R.drawable.ovidiu_2)

        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = viewPagerAdapter.count - 1
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = "Sobre mí"
        tabLayout.getTabAt(1)?.text = "Trabajos"
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
