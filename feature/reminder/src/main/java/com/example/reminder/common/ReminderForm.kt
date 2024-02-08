package com.example.reminder.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ReminderTime
import com.example.reminder.R
import com.example.ui.theme.BaseAppTheme

data class ReminderFormState(
    // title
    val title: String,
    val onTitleChange: (String) -> Unit,
    // times
    val times: List<ReminderTime>
)

@Composable
fun rememberReminderFormState(
    title: String,
    onTitleChange: (String) -> Unit,
): ReminderFormState {
    return remember(
        title,
        onTitleChange,
    ) {
        ReminderFormState(
            title = title,
            onTitleChange = onTitleChange,
            times = listOf(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderForm(
    state: ReminderFormState,
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                stringResource(id = R.string.reminder_form_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        TextField(
            value = state.title,
            onValueChange = state.onTitleChange,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(id = R.string.reminder_form_notification_frequency),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Icon(Icons.Filled.AddCircleOutline, contentDescription = "")
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.times) {
                val text = "${it.hour}:${it.minute}"
                Box {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text)
                        Icon(Icons.Filled.Close, contentDescription = "")
                    }
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.Gray)
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderFormPreview() {
    BaseAppTheme {
        ReminderForm(
            state = ReminderFormState(
                title = "title",
                onTitleChange = {},
                times = listOf(
                    ReminderTime(12, 0),
                    ReminderTime(18, 0),
                ),
            )
        )
    }
}