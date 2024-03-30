package com.nqmgaming.todoapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nqmgaming.todoapp.R
import com.nqmgaming.todoapp.adapter.NoteAdapter
import com.nqmgaming.todoapp.model.Note
import com.nqmgaming.todoapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private val noteViewModal:NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    private val noteAdapter:NoteAdapter by lazy {
        NoteAdapter(this, onClick, onDelete)
    }

    private lateinit var recycler_view: RecyclerView
    private lateinit var btnAddNote: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recycler_view = findViewById(R.id.recycler_view)
        btnAddNote = findViewById(R.id.button_add_note)
        initControls()
        initEvents()
    }

    private fun initEvents() {
        btnAddNote.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
       val adapter = NoteAdapter (this@MainActivity, onClick, onDelete)
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.adapter = adapter


        noteViewModal.getAllNotes().observe(this, Observer {
            adapter.setNotes(it)
        })

    }
    private val onClick:(Note) -> Unit = {
        val intent = Intent(this@MainActivity, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)
    }

    private val onDelete:(Note) -> Unit = {
        noteViewModal.deleteNote(it)
    }
}