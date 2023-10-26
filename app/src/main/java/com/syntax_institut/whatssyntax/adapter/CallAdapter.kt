package com.syntax_institut.whatssyntax.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.databinding.ItemCallBinding


class CallAdapter(
    private val context: Context,
    private val dataset: List<Call>
): RecyclerView.Adapter<CallAdapter.CallViewHolder>() {

    inner class CallViewHolder(val binding: ItemCallBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding = ItemCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvCallName.text = item.contact.name
        holder.binding.tvCallTime.text = item.time
        holder.binding.tvCallContactImage.setImageResource(item.contact.image)

        if (item.accepted) {
            holder.binding.ivCallStatus.setImageResource(R.drawable.icon_call_accepted)
            if (item.incoming) {
                holder.binding.ivCallStatus.rotation = 180F
            }
        } else {
            holder.binding.ivCallStatus.setImageResource(R.drawable.icon_call_missed)
            if (item.incoming) {
                holder.binding.ivCallStatus.rotation = 180F
            }
        }

        holder.binding.cvCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${item.contact.number}")
            startActivity(context, intent, null)
        }

    }

}