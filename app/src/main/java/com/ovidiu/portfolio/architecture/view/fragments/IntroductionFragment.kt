package com.ovidiu.portfolio.architecture.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ovidiu.portfolio.MainApplication
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.ContactType
import com.ovidiu.portfolio.architecture.view.fragments.base_fragments.ViewBindingFragment
import com.ovidiu.portfolio.architecture.viewmodel.ProfessionalViewModel
import com.ovidiu.portfolio.databinding.FragmentIntroductionBinding
import com.ovidiu.portfolio.support.asCircle
import javax.inject.Inject

class IntroductionFragment : ViewBindingFragment<FragmentIntroductionBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ProfessionalViewModel> { viewModelFactory }

    private lateinit var contactPhone: String
    private lateinit var contactEmail: String
    private lateinit var contactGithub: String
    private lateinit var contactLinkedin: String

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as MainApplication).applicationComponent.inject(this)
        super.onAttach(context)
    }

    override fun getViewBinding(inflater: LayoutInflater,container: ViewGroup?): FragmentIntroductionBinding {
        return FragmentIntroductionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        observeData()
    }

    private fun setUp() {
        binding.phoneContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contactPhone"))
            startActivity(intent)
        }

        binding.emailContact.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.fromParts("mailto", contactEmail, null)
                putExtra(Intent.EXTRA_SUBJECT, "Puesto programador Android")
                putExtra(Intent.EXTRA_TEXT, "Hola Ovidiu, he visto tu portfolio y me gustaría contratarte...")
            }
            startActivity(Intent.createChooser(emailIntent, "Enviar email vía..."))
        }

        binding.githubContact.setOnClickListener {
            val webpage: Uri = Uri.parse(contactGithub)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        }

        binding.linkedinContact.setOnClickListener {
            val webpage: Uri = Uri.parse(contactLinkedin)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        }

        binding.btnKnowMoreAboutMe.setOnClickListener (
            Navigation.createNavigateOnClickListener(IntroductionFragmentDirections.actionIntroductionFragmentToProfileFragment())
        )
    }

    private fun observeData() {
        viewModel.loadProfessionalByNameAndSurname("Ovidiu", "Balaban")

        viewModel.professionalLoaded.observe(viewLifecycleOwner, Observer {
            if(it) {
                binding.backgroundLoading.visibility = View.GONE
                binding.loading.hide()
            } else {
                binding.backgroundLoading.visibility = View.VISIBLE
                binding.loading.show()
            }
        })

        viewModel.professional.observe(viewLifecycleOwner, Observer {
            binding.professional = it
        })

        viewModel.profileImageUrl.observe(viewLifecycleOwner, Observer {
            it?.let { url -> binding.imageIntroduction.asCircle(url) }
        })

        viewModel.contactList.observe(viewLifecycleOwner, Observer { contacts ->
            contactPhone = contacts.first { it.contactType == ContactType.PHONE.type }.value
            contactEmail = contacts.first { it.contactType == ContactType.EMAIL.type }.value
            contactGithub = contacts.first { it.contactType == ContactType.GITHUB.type }.value
            contactLinkedin = contacts.first { it.contactType == ContactType.LINKEDIN.type }.value
        })
    }
}
