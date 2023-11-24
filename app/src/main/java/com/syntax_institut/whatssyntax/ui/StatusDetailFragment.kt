package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.FragmentStatusDetailBinding

class StatusDetailFragment: Fragment() {

    private lateinit var binding: FragmentStatusDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatusDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentStatus.observe(viewLifecycleOwner) { status ->

            binding.ivStatus.load(BASE_URL + status.images.first())
            var next = 1

            binding.ivStatus.setOnClickListener {
                if (next != status.images.size) {
                    binding.ivStatus.load(BASE_URL + status.images[next])
                    next++
                } else {
                    findNavController().navigate(R.id.statusFragment)
                }
            }
        }
    }
}