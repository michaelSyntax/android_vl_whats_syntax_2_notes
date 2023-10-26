package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.data.model.Profile
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        val profile = mainActivity.datasource.getProfile()

        binding.ivProfile.setImageResource(profile.image)
        binding.tietProfileName.setText(profile.name)
        binding.tietProfileNumber.setText(profile.number)

        binding.btProfileSave.setOnClickListener {
            val name = binding.tietProfileName.text.toString()
            val number = binding.tietProfileNumber.text.toString()

            if (name != "" && number != "") {
                mainActivity.datasource.setProfile(Profile(name, number, profile.image))
            }
        }
    }
}