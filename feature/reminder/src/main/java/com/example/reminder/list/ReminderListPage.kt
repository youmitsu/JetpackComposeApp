package com.example.reminder.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.model.Reminder
import com.example.ui.theme.BaseAppTheme
import java.util.Date

@Composable
fun ReminderListPageHost(
    navController: NavController,
    viewModel: ReminderListPageViewModel = hiltViewModel(),
) {
    val pageState = rememberReminderListPageState(
        viewModel = viewModel,
        navController = navController,
    )
    LaunchedEffect(Unit) {
        viewModel.load()
    }

    ReminderListPage(
        pageState = pageState,
    )
}

@Composable
internal fun ReminderListPage(
    pageState: ReminderListPageState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
    ) {
        items(pageState.reminders) { reminder ->
            ReminderItem(
                id = reminder.id,
                title = reminder.title,
                enabled = reminder.enabled,
                onClick = pageState.onClickListItem,
                onCheckedChange = { pageState.onSwitchChanged(reminder, it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReminderItem(
    id: String,
    title: String,
    enabled: Boolean,
    onClick: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(130.dp),
        onClick = {
            onClick(id)
        },
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title)
                Switch(checked = enabled, onCheckedChange = onCheckedChange)
            }
            Divider()
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AssistChip(onClick = { /*TODO*/ }, label = { Text("毎日") })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderListPagePreview() {
    BaseAppTheme {
        ReminderListPage(
            pageState = ReminderListPageState(
                reminders = listOf(
                    Reminder(
                        id = "1",
                        title = "リマインダーその1",
                        enabled = true,
                        createdAt = Date(),
                    ),
                ),
                onClickListItem = {},
                onSwitchChanged = { _, _ -> }
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReminderListPagePreviewDark() {
    BaseAppTheme {
        ReminderListPage(
            pageState = ReminderListPageState(
                reminders = listOf(
                    Reminder(
                        id = "1",
                        title = "リマインダーその1",
                        enabled = true,
                        createdAt = Date(),
                    ),
                ),
                onClickListItem = {},
                onSwitchChanged = { _, _ -> }
            )
        )
    }
}
