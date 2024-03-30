package com.nqmgaming.todoapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nqmgaming.todoapp.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(
        note: Note
    )

    @Update
    suspend fun updateNote(
        note: Note
    )

    @Delete
    suspend fun deleteNote(
        note: Note
    )

    @Query("SELECT * FROM note_table")
    fun getAllNotes() :LiveData<List<Note>>

    // get by id
    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Int): LiveData<Note>

    // delete by id
    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

    // update by id
    @Query("UPDATE note_table SET title = :title, description = :description WHERE id = :id")
    suspend fun updateNoteById(id: Int, title: String, description: String)
}