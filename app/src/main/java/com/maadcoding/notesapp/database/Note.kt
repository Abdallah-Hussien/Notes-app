package com.maadcoding.notesapp.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Created by Abdallah Hussien
 * Email: abdallah.hussien.muhammad@gmail.com
 */

@Serializable
@Entity(tableName = "note")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int =0,
    var content: String
)