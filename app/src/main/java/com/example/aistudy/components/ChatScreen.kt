package com.example.aistudy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.graphics.Bitmap
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.aistudy.ui.viewmodels.ChatUiEvent
import com.example.aistudy.ui.viewmodels.ChatViewModel
import kotlinx.coroutines.flow.update

@Composable
fun ChatScreen(
    paddingValues: PaddingValues,
    chatViewModel: ChatViewModel,
    imagePickerLauncher: (PickVisualMediaRequest) -> Unit,
    bitmap: Bitmap?
) {
    val chatState = chatViewModel.chatState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            reverseLayout = true
        ) {
            itemsIndexed(chatState.chatList) { index, chat ->
                if (chat.isFromUser) {
                    UserChatItem(
                        prompt = chat.prompt, bitmap = chat.bitmap
                    )
                } else {
                    ModelChatItem(response = chat.prompt)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                bitmap?.let {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(bottom = 2.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentDescription = "picked image",
                        contentScale = ContentScale.Crop,
                        bitmap = it.asImageBitmap()
                    )
                }
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            // Correct usage: directly call the function with the request
                            imagePickerLauncher(
                                PickVisualMediaRequest.Builder()
                                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    .build()
                            )
                        },
                    imageVector = Icons.Rounded.AddPhotoAlternate,
                    contentDescription = "Add Photo",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                modifier = Modifier
                    .weight(1f),
                value = chatState.prompt,
                onValueChange = {
                    chatViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                },
                placeholder = {
                    Text(text = "Type a prompt")
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        chatViewModel.onEvent(ChatUiEvent.SendPrompt(chatState.prompt, bitmap))
                    },
                imageVector = Icons.AutoMirrored.Rounded.Send,
                contentDescription = "Send prompt",
                tint = MaterialTheme.colorScheme.primary
            )

        }

    }
}
