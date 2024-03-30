package com.nqmgaming.todoapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.nqmgaming.todoapp.R
import com.nqmgaming.todoapp.model.Note
import com.nqmgaming.todoapp.viewmodel.NoteViewModel

class AddNoteActivity : AppCompatActivity() {
    private val noteViewModal: NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }

    private lateinit var btnAddNote: Button
    private lateinit var title: EditText
    private lateinit var description: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnAddNote = findViewById(R.id.button_save_note)
        title = findViewById(R.id.input_note_title)
        description = findViewById(R.id.input_note_content)
        btnAddNote.setOnClickListener{
            val note=Note(
                title = title.text.toString(),
                description = description.text.toString()
            )
            noteViewModal.insertNote(note)
            finish()
        }
    }
}