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

class UpdateNoteActivity : AppCompatActivity() {
    private val noteViewModal: NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    private lateinit var TitleEt: EditText
    private lateinit var DescriptionEt: EditText
    private lateinit var buttonSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        TitleEt = findViewById(R.id.input_note_title)
        DescriptionEt = findViewById(R.id.input_note_content)
        buttonSave = findViewById(R.id.button_update_note)
        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        TitleEt.setText(note.title)
        DescriptionEt.setText(note.description)


        buttonSave.setOnClickListener{
           note.title = TitleEt.text.toString()
            note.description = DescriptionEt.text.toString()
            noteViewModal.updateNote(note)
            finish()
        }
    }
}