package com.syntax_institut.whatssyntax.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.ItemChatInBinding
import com.syntax_institut.whatssyntax.databinding.ItemChatOutBinding

class ChatDetailAdapter(
    private val dataset: List<Message>
): RecyclerView.Adapter<ViewHolder>() {

    private val outType = 1
    private val inType = 2

    inner class MessageOutViewHolder(val binding: ItemChatOutBinding): RecyclerView.ViewHolder(binding.root)

    inner class MessageInViewHolder(val binding: ItemChatInBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        val item = dataset[position]
        return if (item.incoming) {
            inType
        } else {
            outType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == inType) {
            val binding = ItemChatInBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageInViewHolder(binding)
        } else {
            val binding = ItemChatOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageOutViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]

        if (holder is MessageInViewHolder) {
            holder.binding.tvMessageIn.text = item.text
        } else if (holder is MessageOutViewHolder) {
            holder.binding.tvMessageOut.text = item.text
        }
    }

}