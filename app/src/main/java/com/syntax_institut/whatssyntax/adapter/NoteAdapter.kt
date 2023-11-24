package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.model.Note
import com.syntax_institut.whatssyntax.databinding.ItemNoteBinding

class NoteAdapter(
    private val dataset: List<Note>,
    private val viewModel: MainViewModel
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvNoteName.text = item.contactName
        holder.binding.tvNoteText.text = item.text

        holder.binding.cvNote.setOnLongClickListener {
            viewModel.deleteNote(item)
            true
        }

    }
}