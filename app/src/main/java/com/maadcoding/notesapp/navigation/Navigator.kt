package com.maadcoding.notesapp.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.maadcoding.notesapp.ui.activity.AddNoteScreen
import com.maadcoding.notesapp.ui.activity.EditNoteScreen
import com.maadcoding.notesapp.ui.activity.HomeScreen

/**
 * Created by Abdallah Hussien
 * Email: abdallah.hussien.muhammad@gmail.com
 */

@Composable
fun NavApp(modifier: Modifier) {
    val backStack = rememberNavBackStack(Home)

    val entry = entryProvider {
        entry<Home> {
            HomeScreen(
                addNote = {
                    backStack.add(AddNote)
                },
                editNote = { it ->
                    backStack.add(EditNote(it))
                }
            )
        }

        entry<AddNote> {
            AddNoteScreen {
                backStack.removeLastOrNull()
            }
        }

        entry<EditNote> {
            EditNoteScreen(note = it.note){
                backStack.removeLastOrNull()
            }
        }
    }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = entry,
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                targetOffsetX = { -it })
        }
    )
}