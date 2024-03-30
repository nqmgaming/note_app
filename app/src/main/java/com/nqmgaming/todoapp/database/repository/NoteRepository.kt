package com.nqmgaming.todoapp.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.nqmgaming.todoapp.database.NoteDatabase
import com.nqmgaming.todoapp.model.Note

class NoteRepository (
    application: Application
){

    private val noteDao = NoteDatabase.getInstance(application).getNoteDao()

    suspend fun insertNote(
        note: Note
    ) = noteDao.insertNote(note)

    suspend fun updateNote(
        note: Note
    ) = noteDao.updateNote(note)

    suspend fun deleteNote(
        note: Note
    ) = noteDao.deleteNote(note)

    fun getAllNotes() :LiveData<List<Note>> = noteDao.getAllNotes()

}