package com.example.reminder.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reminder.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderForm(
    title: String,
    onTitleChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(stringResource(id = R.string.reminder_form_title))
        TextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        Text(stringResource(id = R.string.reminder_form_notification_frequency))
        ListItem(
            headlineText = {
                Text("毎日10時")
            },
            trailingContent = {
                Icon(Icons.Filled.ArrowRight, contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                )
                .clickable {
                    // TODO:
                },
        )
        Spacer(Modifier.height(16.dp))
//                Text(stringResource(id = R.string.reminder_form_notification_target))
//                ListItem(
//                    headlineText = {
//                        Text(stringResource(id = R.string.reminder_form_notification_target_all))
//                    },
//                    trailingContent = {
//                        Icon(Icons.Filled.ArrowRight, contentDescription = "")
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(
//                            width = 1.dp,
//                            color = Color.Gray,
//                        )
//                        .clickable {
//                            // TODO:
//                        },
//                )
    }
}