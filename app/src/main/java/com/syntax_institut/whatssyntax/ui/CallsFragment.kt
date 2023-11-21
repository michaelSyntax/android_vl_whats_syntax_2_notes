package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.adapter.CallAdapter
import com.syntax_institut.whatssyntax.databinding.FragmentCallsBinding

class CallsFragment: Fragment() {

    private lateinit var binding: FragmentCallsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCallsBinding.inflate(layoutInflater)
        viewModel.getCalls()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.calls.observe(viewLifecycleOwner) {
            binding.rvCalls.adapter = CallAdapter(requireContext(), it)
        }

    }
}