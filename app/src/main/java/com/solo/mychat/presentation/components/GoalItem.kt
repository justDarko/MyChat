package com.solo.mychat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun GoalItem(
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: ImageVector = Icons.Default.Check,
    goalTitle: String,
    goalStatus: String,
    onCompleteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF264533),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .height(56.dp)
                .width(56.dp),
            contentAlignment = Center
        ) {
            IconButton(
                onClick = onCompleteClick
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Status icon"
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = modifier.weight(1f)) {
            Text(text = goalTitle)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = goalStatus)
        }

        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF264533),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .height(56.dp)
                .width(56.dp),
            contentAlignment = Center
        ) {
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete goal"
                )
            }
        }
    }
}