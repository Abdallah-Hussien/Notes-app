package com.maadcoding.notesapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maadcoding.notesapp.database.Note
import com.maadcoding.notesapp.database.RoomDBHelper
import kotlinx.coroutines.launch

/**
 * Created by Abdallah Hussien
 * Email: abdallah.hussien.muhammad@gmail.com
 */
class NoteVM(app: Application) : AndroidViewModel(app) {
    val db = RoomDBHelper.getInstance(app).dao

    fun upsert (n: Note){
        viewModelScope.launch{
            db.addNote(n)
        }
    }

    fun delete(n: Note){
        viewModelScope.launch{
            db.deleteNote(n)
        }
    }

    fun getAllNotes() = db.getAllNotes()

}