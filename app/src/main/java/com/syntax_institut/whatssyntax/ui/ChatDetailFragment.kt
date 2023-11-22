package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.adapter.ChatDetailAdapter
import com.syntax_institut.whatssyntax.databinding.FragmentChatDetailBinding
import com.syntax_institut.whatssyntax.data.model.Message

class ChatDetailFragment: Fragment() {

    private lateinit var binding: FragmentChatDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = ChatDetailAdapter(mutableListOf(), viewModel)

        viewModel.messages.observe(viewLifecycleOwner) {
            adapter = ChatDetailAdapter(it.toMutableList(), viewModel)
            binding.rvMessages.adapter = adapter
        }

        binding.btSend.setOnClickListener {
            val text = binding.tietMessage.text.toString()
            if (text != "") {
                val message = Message(text = text, incoming =  false)
                viewModel.sendNewMessage(message)
                adapter.sendNewMessage(message)
            }
            binding.tietMessage.setText("")
        }

    }
}