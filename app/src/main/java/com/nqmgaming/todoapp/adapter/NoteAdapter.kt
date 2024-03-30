package com.nqmgaming.todoapp.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nqmgaming.todoapp.R
import com.nqmgaming.todoapp.model.Note

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notes:List<Note> = listOf()
    inner class NoteViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        private val noteTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        private val noteContent = itemView.findViewById<TextView>(R.id.text_view_content)
        private val noteDelete = itemView.findViewById<TextView>(R.id.button_delete)
        private val noteEdit = itemView.findViewById<TextView>(R.id.button_edit)

        fun onBind(note: Note) {

            noteTitle.text = note.title
            noteContent.text = note.description

            noteDelete.setOnClickListener{
                onDelete(note)
            }

            noteEdit.setOnClickListener{
                onClick(note)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val itemView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}