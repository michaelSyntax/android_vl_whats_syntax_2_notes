package com.syntax_institut.whatssyntax.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ChatDetailAdapter
import com.syntax_institut.whatssyntax.databinding.FragmentChatDetailBinding
import com.syntax_institut.whatssyntax.data.model.Message

class ChatDetailFragment: Fragment() {

    private lateinit var binding: FragmentChatDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var position = 0

        arguments?.let {
            position = it.getInt("position")
        }

        val mainActivity = activity as MainActivity
        val chats = mainActivity.datasource.getChats()

        val messages = chats[position].messages
        binding.rvMessages.adapter = ChatDetailAdapter(messages)

        binding.btSend.setOnClickListener {
            val messageText = binding.tietMessage.text.toString()
            if (messageText != "") {
                val newMessage = Message(messageText, false)
                messages.add(newMessage)
                binding.rvMessages.adapter = ChatDetailAdapter(messages)
                binding.tietMessage.setText("")
            }
        }
    }
}