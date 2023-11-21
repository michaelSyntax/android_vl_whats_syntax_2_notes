package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        viewModel.getProfile()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var image = ""

        viewModel.profile.observe(viewLifecycleOwner) {
            binding.tietProfileNumber.setText(it.number)
            binding.tietProfileName.setText(it.name)
            binding.ivProfile.load(BASE_URL + it.image)
            image = it.image
        }

        binding.btProfileSave.setOnClickListener {
            val name = binding.tietProfileName.text.toString()
            val number = binding.tietProfileNumber.text.toString()
            if (number != "" && name != "") {
                viewModel.setProfile(Profile(name, number, image))
            }
        }

    }
}