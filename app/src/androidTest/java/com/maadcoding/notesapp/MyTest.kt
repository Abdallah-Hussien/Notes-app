package com.maadcoding.notesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maadcoding.notesapp.database.Note
import com.maadcoding.notesapp.database.NoteDao
import com.maadcoding.notesapp.database.RoomDBHelper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Abdallah Hussien
 * Email: abdallah.hussien.muhammad@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class MyTest {
    private lateinit var database: RoomDBHelper
    private lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            RoomDBHelper::class.java
        )
            .allowMainThreadQueries()
            .build()

        noteDao = database.dao
    }

    @After
    fun tearDown() {
        database.close()
    }
    @Test
    fun insertNoteAndSelectAllNotes() = runTest {
        // Arrange: create a note
        val note = Note(
            id = 0,
            content = "Testing Room database"
        )

        // Act: insert the note
        noteDao.addNote(note)

        // Act: get all notes from the database
        val notes = noteDao.getAllNotes().first()

        // Assert: verify the result
        Assert.assertEquals(1, notes.size)
        Assert.assertEquals("Testing Room database", notes[0].content)
    }
}