package com.maadcoding.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Abdallah Hussien
 * Email: abdallah.hussien.muhammad@gmail.com
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class RoomDBHelper : RoomDatabase() {

    abstract val dao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null

        fun getInstance(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomDBHelper::class.java,
                    "room_db"
                ).build()
                return INSTANCE!!
            }
        }
    }
}
