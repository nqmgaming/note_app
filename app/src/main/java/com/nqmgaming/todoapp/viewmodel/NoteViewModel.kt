package com.nqmgaming.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nqmgaming.todoapp.database.repository.NoteRepository
import com.nqmgaming.todoapp.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):ViewModel() {
    private val noteRepository = NoteRepository(application)

    fun insertNote(
        note: Note
    ) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(
        note: Note
    ) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(
        note: Note
    ) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<Note>> = noteRepository.getAllNotes()


    class NoteViewModelFactory(
        private val application: Application
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
                return NoteViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}