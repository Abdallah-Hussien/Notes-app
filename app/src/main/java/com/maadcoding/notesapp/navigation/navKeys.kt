package com.maadcoding.notesapp.navigation

import androidx.navigation3.runtime.NavKey
import com.maadcoding.notesapp.database.Note
import kotlinx.serialization.Serializable

/**
 * Created by Abdallah Hussien
 * Email: abdallah.hussien.muhammad@gmail.com
 */

@Serializable
data object AddNote: NavKey

@Serializable
data object Home: NavKey
@Serializable
data class EditNote(val note: Note): NavKey