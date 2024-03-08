package com.example.aistudy.ui.screens.note

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aistudy.components.CustomText
import com.example.aistudy.components.DisplayAlertDialog
import com.example.aistudy.data.models.Note
import com.example.aistudy.data.models.Priority
import com.example.aistudy.ui.theme.BlackOlive
import com.example.aistudy.utils.Action
import java.util.*
import com.example.aistudy.R
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

@Composable
fun NoteAppBar(
    navigateToListScreen: (Action) -> Unit,
    navigateToChatbotScreen: () -> Unit,
    navigateToImage2TextScreen: () -> Unit,
    navigateToSpeech2TextScreen:() -> Unit,
    selectedNote: Note?
) {
    if (selectedNote == null) {
        NewNoteAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        EditNoteAppBar(note = selectedNote, navigateToListScreen = navigateToListScreen, navigateToChatbotScreen = navigateToChatbotScreen, navigateToImage2TextScreen = navigateToImage2TextScreen, navigateToSpeech2TextScreen = navigateToSpeech2TextScreen)
    }
}


@Composable
fun NewNoteAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        navigationIcon = {
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
            BackButton(backButtonPressed = navigateToListScreen)
        },
        title = {
            CustomText(
                text = stringResource(id = R.string.add_note),
                color = MaterialTheme.colors.secondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
        }
    )
}

@Composable
fun BackButton(backButtonPressed: (Action) -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable { backButtonPressed(Action.NO_ACTION) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun AddNoteButton(addNoteButtonPressed: (Action) -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable { addNoteButtonPressed(Action.ADD) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = stringResource(id = R.string.save_note_action),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun EditNoteAppBar(note: Note, navigateToListScreen: (Action) -> Unit, navigateToChatbotScreen: () -> Unit, navigateToImage2TextScreen: () -> Unit, navigateToSpeech2TextScreen: () -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        navigationIcon = {
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
            BackButton(backButtonPressed = navigateToListScreen)
        },
        title = {
            CustomText(
                text = stringResource(id = R.string.edit_note),
                color = MaterialTheme.colors.secondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            EditNoteAppBarActions(note = note, navigateToListScreen = navigateToListScreen, navigateToChatbotScreen = navigateToChatbotScreen, navigateToImage2TextScreen = navigateToImage2TextScreen, navigateToSpeech2TextScreen = navigateToSpeech2TextScreen)
            Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
        }
    )
}

@Composable
fun EditNoteAppBarActions(note: Note, navigateToListScreen: (Action) -> Unit, navigateToChatbotScreen: () -> Unit, navigateToImage2TextScreen: () -> Unit, navigateToSpeech2TextScreen: () -> Unit) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_note_alert_title),
        message = stringResource(id = R.string.delete_note_alert_message),
        openDialog = openDialog,
        button1Text = "No",
        button2Text = "Yes",
        onButton1Pressed = { openDialog = false },
        onButton2Pressed = {
            openDialog = false
            navigateToListScreen(Action.DELETE)
        })

    ChatbotButton(navigateToChatbotScreen = navigateToChatbotScreen)
    Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
    AddPhotoButton(
        onImagePicked = { /* Placeholder for future implementation */}
    )
    Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
    AddToTextButton(navigateToImage2TextScreen = navigateToImage2TextScreen, navigateToSpeech2TextScreen = navigateToSpeech2TextScreen)
    Divider(modifier = Modifier.width(12.dp), color = MaterialTheme.colors.primary)
}

@Composable
fun EditNoteButton(editNoteButtonPressed: (Action) -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable { editNoteButtonPressed(Action.UPDATE) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = stringResource(id = R.string.edit_note_action),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun DeleteNoteButton(deleteNoteButtonPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable { deleteNoteButtonPressed() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = stringResource(id = R.string.delete_note_action),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun AddToTextButton(
    navigateToImage2TextScreen: () -> Unit,
    navigateToSpeech2TextScreen:() -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable { expanded = true },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add_to_text),
            contentDescription = stringResource(id = R.string.add_text_action),
            tint = MaterialTheme.colors.secondary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(200.dp)
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    navigateToImage2TextScreen()
                }
            ) {
                Text("ImageReader")
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    navigateToSpeech2TextScreen()
                }
            ) {
                Text("Transcriber")
            }
        }
    }
}

@Composable
fun AddPhotoButton(
    onImagePicked: (Uri?) -> Unit
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    // Remember a launcher for permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted, you can proceed with opening camera or gallery
                showDialog = true
            } else {
                // Handle permission denial gracefully
            }
        }
    )

    // Remember a launcher for taking a picture and saving it
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap: Bitmap? ->
            // Convert bitmap to Uri or handle it directly
        }
    )

    // Remember a launcher for picking an image
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = onImagePicked
    )

    // This checks for permission and shows the dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Add Photo") },
            text = { Text(text = "Choose an action") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        takePictureLauncher.launch(null)
                    }
                ) {
                    Text(text = "Take Photo")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        pickImageLauncher.launch("image/*")
                    }
                ) {
                    Text(text = "Pick from Gallery")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
            .clickable {
                // Check for camera permission before showing dialog
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.CAMERA
                    ) -> {
                        showDialog = true
                    }
                    else -> {
                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add_photo),
            contentDescription = stringResource(id = R.string.add_photo_action),
            tint = MaterialTheme.colors.secondary
        )
    }
}




@Composable
fun ChatbotButton(navigateToChatbotScreen: () -> Unit) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color = BlackOlive, shape = RoundedCornerShape(10.dp))
                .clickable { navigateToChatbotScreen() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_chatbot),
            contentDescription = stringResource(id = R.string.chatbot_action),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
@Preview
fun NoteAppBarPreview() {
    NoteAppBar(
        navigateToListScreen = { /* handle navigation */ },
        navigateToChatbotScreen = { /* handle navigation */ },
        selectedNote = null,
        navigateToImage2TextScreen = { /* handle navigation */ },
        navigateToSpeech2TextScreen = { /* handle navigation */ }
    )
}

@Composable
@Preview
fun EditNoteAppBarPreview() {
    EditNoteAppBar(
        note = Note(
            id = 0,
            title = "UI concepts worth existing",
            description = "UI concepts worth existing",
            priority = Priority.HIGH,
            reminderDateTime = null,
            workerRequestId = null,
            createdAt = Date(),
            updatedAt = Date(),
            categoryId = 1
        ),
        navigateToListScreen = { /* handle navigation */ },
        navigateToChatbotScreen = { /* handle navigation */ },
        navigateToImage2TextScreen = { /* handle navigation */ },
        navigateToSpeech2TextScreen = { /* handle navigation */ }
    )
}
