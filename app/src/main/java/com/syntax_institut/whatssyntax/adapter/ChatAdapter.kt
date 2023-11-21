package com.syntax_institut.whatssyntax.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.ItemChatBinding

class ChatAdapter(
    private val dataset: MutableList<Chat>,
    private val viewModel: MainViewModel
): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(val binding: ItemChatBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.ivChatContactImage.load(BASE_URL + item.contact.image)
        holder.binding.tvChatContactName.text = item.contact.name
        holder.binding.tvChatLastMess.text = item.lastMessage.text

        holder.binding.cvChat.setOnClickListener {
            viewModel.getMessages(item.id)
            holder.itemView.findNavController().navigate(R.id.chatDetailFragment)
        }
    }

}