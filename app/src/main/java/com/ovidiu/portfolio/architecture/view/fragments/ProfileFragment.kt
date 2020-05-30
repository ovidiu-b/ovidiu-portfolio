package com.ovidiu.portfolio.architecture.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ovidiu.portfolio.MainApplication
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.ContactType
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.AboutMeFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.ExperienceFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.StudyFragmentTab
import com.ovidiu.portfolio.architecture.view.fragments.profile_tab_fragments.adapters.TimelineRecyclerViewAdapter.TimelineItemDataModel
import com.ovidiu.portfolio.architecture.viewmodel.ProfessionalViewModel
import com.ovidiu.portfolio.databinding.FragmentProfileBinding
import com.ovidiu.portfolio.support.DateTimeUtils
import com.ovidiu.portfolio.support.circleDrawable
import javax.inject.Inject

class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ProfessionalViewModel> { viewModelFactory }

    private lateinit var contactPhone: String
    private lateinit var contactEmail: String
    private lateinit var contactGithub: String
    private lateinit var contactLinkedin: String

    private lateinit var viewPagerAdapter: ProfileFragmentPageAdapter

    private lateinit var professionalCompleteName: String
    private lateinit var professionalImageUrl: String

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as MainApplication).applicationComponent.inject(this)
        super.onAttach(context)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar = binding.topAppBar
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        viewPagerAdapter = ProfileFragmentPageAdapter(activity?.supportFragmentManager!!)

        binding.imageProfile.circleDrawable(R.drawable.ovidiu)

        binding.imageProfile.setOnClickListener {
            it.findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToImageFullSizeFragment(
                    professionalCompleteName,
                    professionalImageUrl
                )
            )
        }

        if (topAppBar.menu is MenuBuilder) {
            (topAppBar.menu as MenuBuilder).setOptionalIconsVisible(true)
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.callMe -> {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contactPhone"))
                    startActivity(intent)

                    true
                }
                R.id.mailMe -> {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.fromParts("mailto", contactEmail, null)
                        putExtra(Intent.EXTRA_SUBJECT, "Puesto programador Android")
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Hola Ovidiu, he visto tu portfolio y me gustaría contratarte..."
                        )
                    }
                    startActivity(Intent.createChooser(emailIntent, "Enviar email vía..."))

                    true
                }
                R.id.github -> {
                    val webpage: Uri = Uri.parse(contactGithub)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    startActivity(intent)

                    true
                }
                R.id.linkedin -> {
                    val webpage: Uri = Uri.parse(contactLinkedin)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    startActivity(intent)

                    true
                }
                else -> false
            }
        }

        topAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = viewPagerAdapter.count - 1
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = "Sobre mí"
        tabLayout.getTabAt(1)?.text = "Trabajos"
        tabLayout.getTabAt(2)?.text = "Estudios"

        observeData()
    }

    private fun observeData() {
        viewModel.loadProfessionalByNameAndSurname("Ovidiu", "Balaban")

        viewModel.professional.observe(viewLifecycleOwner, Observer {
            binding.professional = it
            viewPagerAdapter.aboutMeFragmentTab.setProfessional(it)

            professionalCompleteName = it.name + " " + it.surname
        })

        viewModel.profileImageUrl.observe(viewLifecycleOwner, Observer {
            professionalImageUrl = it
        })

        viewModel.experienceList.observe(viewLifecycleOwner, Observer {
            viewPagerAdapter.experienceFragmentTab.setExperienceList(it.map { experience ->
                TimelineItemDataModel(
                    experience.company,
                    experience.job,
                    experience.description,
                    getTimeLineRangeYearFormatted(experience.dateBegin, experience.dateEnd)
                )
            })
        })

        viewModel.studyList.observe(viewLifecycleOwner, Observer {
            viewPagerAdapter.studyFragmentTab.setStudyList(it.map { study ->
                TimelineItemDataModel(
                    study.title,
                    study.school,
                    study.description,
                    getTimeLineRangeYearFormatted(study.dateBegin, study.dateEnd)
                )
            })
        })

        viewModel.contactList.observe(viewLifecycleOwner, Observer { contacts ->
            contactPhone = contacts.first { it.contactType == ContactType.PHONE.type }.value
            contactEmail = contacts.first { it.contactType == ContactType.EMAIL.type }.value
            contactGithub = contacts.first { it.contactType == ContactType.GITHUB.type }.value
            contactLinkedin = contacts.first { it.contactType == ContactType.LINKEDIN.type }.value
        })
    }

    private fun getTimeLineRangeYearFormatted(dateBegin: Long, dateEnd: Long?): String {
        val dateBeginFormatted = DateTimeUtils.millsToDate(dateBegin)
        val dateEndFormatted = dateEnd?.let { DateTimeUtils.millsToDate(it) } ?: "actualidad"

        return "$dateBeginFormatted - $dateEndFormatted"
    }
}

private class ProfileFragmentPageAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val aboutMeFragmentTab = AboutMeFragmentTab()
    val experienceFragmentTab = ExperienceFragmentTab()
    val studyFragmentTab = StudyFragmentTab()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> aboutMeFragmentTab
            1 -> experienceFragmentTab
            else -> studyFragmentTab
        }
    }

    override fun getCount() = 3
}
