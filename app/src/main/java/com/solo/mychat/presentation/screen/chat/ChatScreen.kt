package com.solo.mychat.presentation.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.mychat.presentation.components.ChatInputField
import com.solo.mychat.presentation.components.MyMessageItem
import com.solo.mychat.presentation.components.OtherMessageItem

@Composable
fun ChatScreen(
    modifier: Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    var message by remember { mutableStateOf("") }
    val state = viewModel.state.collectAsState()

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
            reverseLayout = false,
            state = LazyListState(state.value.listOfMessages.size)
        ) {
            items(state.value.listOfMessages, key = { item -> item.id }) { message ->
                if (message.isFromMe) {
                    MyMessageItem(message)
                } else {
                    OtherMessageItem(message)
                }
            }
        }
        if (state.value.waitingOnResponse) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        // Input field at the bottom
        ChatInputField(
            value = message,
            onValueChange = { message = it },
            onSend = {
                if (message.isNotBlank()) {
                    viewModel.onAction(ChatActions.SendMessage(message = message))
                    println("Sending: $message")
                    message = ""
                }
            }
        )
    }
}
