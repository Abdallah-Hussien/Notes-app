package com.maadcoding.notesapp.ui.activity

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maadcoding.notesapp.database.Note
import com.maadcoding.notesapp.ui.NoteVM

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    noteVM: NoteVM = viewModel(),
    note: Note,
    finish: () -> Unit
) {

    val activity = LocalActivity.current
    val context = LocalContext.current
    val noteFieldState = rememberTextFieldState(note.content)
    var editButtonIsEnabled by rememberSaveable { mutableStateOf(false) }
    editButtonIsEnabled = note.content != noteFieldState.text.toString()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {


        OutlinedTextField(
            state = noteFieldState,
            label = { Text("Note details") },
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                onClick = {
                    note.content = noteFieldState.text.toString()
                    noteVM.upsert(note)
                    Toast.makeText(context, "UpDated successful", Toast.LENGTH_SHORT).show();
                    finish()
                },
                enabled = editButtonIsEnabled,
                modifier = modifier
                    .weight(1f)
            ) {
                Text(text = "Update")
            }
            OutlinedButton(
                onClick = {
                    noteVM.delete(note)
                    finish()
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}