package com.example.aistudy.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aistudy.R
import com.example.aistudy.ui.theme.BlackOlive
import com.example.aistudy.data.models.Category

@Composable
fun PriorityDropDown(
    categories: List<Category>,
    selectedCategory: Category?,
    onCategoryChange: (Category) -> Unit,
    onAddCategory: (String, String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showAddCategoryDialog by remember { mutableStateOf(false) } // State to control the visibility of the dialog


    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp),
            text = "${selectedCategory?.emoji ?: ""} ${selectedCategory?.name ?: "Select Category"}",
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp
        )

        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(degrees = angle),
            //.weight(1.5f),
            onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.down_arrow),
                tint = MaterialTheme.colors.secondary
            )
        }

        DropdownMenu(
            modifier = Modifier
                .background(BlackOlive)
                .fillMaxWidth(fraction = 0.92f),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {

            categories.forEach{ category ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onCategoryChange(category)
                }) {
                    Text(text = "${category.emoji} ${category.name}")
                }
            }
            Divider()
            DropdownMenuItem(onClick = {
                expanded = false
                showAddCategoryDialog = true // Show the add category dialog
            }) {
                Text(
                    "Add New Category",
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.W400, // Match the font weight as used in other menu items
                    fontSize = 16.sp // Match the font size as used in other menu items
                )
            }
        }
    }

    if (showAddCategoryDialog) {
        var newCategoryName by remember { mutableStateOf("") }
        var selectedEmoji by remember { mutableStateOf("") }
        var showEmojiPicker by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = { showAddCategoryDialog = false },
            text = {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (selectedEmoji.isNotEmpty()) {
                            Text(selectedEmoji)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        TextField(
                            value = newCategoryName,
                            onValueChange = { newCategoryName = it },
                            label = { Text("Category Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Button(onClick = { showEmojiPicker = true }) {
                        Text("Select Emoji")
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showAddCategoryDialog = false
                    onAddCategory(newCategoryName, selectedEmoji)
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = { showAddCategoryDialog = false }) {
                    Text("Cancel")
                }
            }
        )
        // Show the emoji picker dialog when requested
        if (showEmojiPicker) {
            EmojiPickerDialog(
                onEmojiSelected = { emoji ->
                    selectedEmoji = emoji
                    showEmojiPicker = false
                },
                onDismissRequest = { showEmojiPicker = false }
            )
        }
    }
}

@Composable
fun EmojiPickerDialog(onEmojiSelected: (String) -> Unit, onDismissRequest: () -> Unit) {
    val emojis = listOf("😀", "😂", "🥰", "😎", "🤔") // Add more emojis as needed

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Select an Emoji") },
        text = {
            LazyColumn {
                items(emojis) { emoji ->
                    TextButton(onClick = { onEmojiSelected(emoji) }) {
                        Text(text = emoji, fontSize = 24.sp)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text("Close")
            }
        }
    )
}
