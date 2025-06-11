package com.solo.mychat.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddGoalDialog(
    modifier: Modifier,
    onDismiss: () -> Unit,
    onAction: (String, Boolean) -> Unit
) {
    var goalTitle by remember { mutableStateOf("") }
    var isAchieved by remember { mutableStateOf(false) }

    AlertDialog(
        modifier = modifier,
        title = { Text(text = "Add goal") },
        text = {
            Column {
                OutlinedTextField(
                    value = goalTitle,
                    onValueChange = { goalTitle = it },
                    label = { Text("Goal Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isAchieved,
                        onCheckedChange = { isAchieved = it }
                    )
                    Text(text = "Achieved")
                }
            }
        },
        onDismissRequest = { onDismiss() },


        confirmButton = {
            TextButton(onClick = {
                onAction(goalTitle, isAchieved)
                goalTitle = ""
                isAchieved = false
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    goalTitle = ""
                    isAchieved = false
                    onDismiss()
                }
            ) {
                Text("Cancel")
            }
        },
    )
}