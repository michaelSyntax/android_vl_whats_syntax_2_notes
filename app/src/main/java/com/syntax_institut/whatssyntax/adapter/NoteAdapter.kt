package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.MainViewModel
import com.syntax_institut.whatssyntax.data.model.Note
import com.syntax_institut.whatssyntax.databinding.ItemNoteBinding


class NoteAdapter(
    private val notes: List<Note>,
    private val viewModel: MainViewModel
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val viewBinding: ItemNoteBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val vb = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(vb)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.viewBinding.tvNoteName.text = note.contactName
        holder.viewBinding.tvNoteText.text = note.text

        holder.viewBinding.root.setOnClickListener {
            //viewModel.deleteNote(note)
            viewModel.deleteNoteById(note.id)
        }
    }
}