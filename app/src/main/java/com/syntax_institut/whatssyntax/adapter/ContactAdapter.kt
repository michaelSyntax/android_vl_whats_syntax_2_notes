package com.syntax_institut.whatssyntax.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.data.remote.BASE_URL
import com.syntax_institut.whatssyntax.databinding.ItemContactBinding
import com.syntax_institut.whatssyntax.ui.StatusFragmentDirections


class ContactAdapter(
    private val dataset: List<Contact>
): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.ivContactImage.load(BASE_URL + item.image)
        holder.binding.tvContactName.text = item.name

        if (item.status == null) {
            val matrix = ColorMatrix()
            matrix.setSaturation(0f)

            val filter = ColorMatrixColorFilter(matrix)
            holder.binding.ivContactImage.colorFilter = filter
        } else {
            val matrix = ColorMatrix()
            matrix.setSaturation(1f)

            val filter = ColorMatrixColorFilter(matrix)
            holder.binding.ivContactImage.colorFilter = filter

            holder.binding.cvContact.setOnClickListener {
                holder.itemView.findNavController().navigate(StatusFragmentDirections.actionStatusFragmentToStatusDetailFragment(item.status.text))
            }
        }
    }

}