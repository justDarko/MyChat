package com.solo.mychat.presentation.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.mychat.domain.models.Message
import com.solo.mychat.presentation.components.MyMessageItem
import com.solo.mychat.presentation.components.OtherMessageItem

@Composable
fun ChatScreen(
    modifier: Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    var message by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val messages = listOf(
        Message(
            id = "1",
            text = "Hello from me",
            isFromMe = true
        ),
        Message(
            id = "2",
            text = "Hello Darko",
            isFromMe = false
        ),
        Message(
            id = "3",
            text = "How are you",
            isFromMe = true
        ),
        Message(
            id = "4",
            text = "Fine. How can i assist you today?",
            isFromMe = false
        ),
        Message(
            id = "5",
            text = "Write me a poem, please",
            isFromMe = true
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Messages list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            state = listState,
            reverseLayout = false
        ) {
            items(messages) { message ->
                if (message.isFromMe) {
                    MyMessageItem(message)
                } else {
                    OtherMessageItem(message)
                }
            }
        }

        // Input field at the bottom
        ChatInputField(
            value = message,
            onValueChange = { message = it },
            onSend = {
                if (message.isNotBlank()) {
                    viewModel.askQuestion(message = message)
                    println("Sending: $message")
                    message = ""
                }
            }
        )
    }
}

@Composable
fun ChatInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Type a message...") },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = androidx.compose.ui.text.input.ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                onSend()
                keyboardController?.hide()
            }
        ),
        trailingIcon = {
            IconButton(onClick = {
                onSend()
                keyboardController?.hide()
            }) {
                Icon(Icons.Default.Send, contentDescription = "Send")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
