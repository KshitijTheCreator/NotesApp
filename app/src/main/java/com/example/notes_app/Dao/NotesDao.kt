package com.example.notes_app.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes_app.Model.Notes

@Dao
interface NotesDao {
    //First query is to get the notes from the database
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Onconflict means when we get the same query what to do (here we "replace" with new input)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notes: Notes)
}