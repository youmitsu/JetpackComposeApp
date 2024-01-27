package com.example.reminder.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ui.theme.BaseAppTheme

@Composable
fun ReminderListPageHost(
    navController: NavController,
) {
    ReminderListPage()
}

@Composable
internal fun ReminderListPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        ReminderItem(
            title = "Title",
            enabled = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReminderItem(
            title = "Title",
            enabled = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReminderItem(
            title = "Title",
            enabled = true,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReminderItem(
    title: String,
    enabled: Boolean,
) {
    Card(
        modifier = Modifier
            .height(130.dp)
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
                Switch(checked = enabled, onCheckedChange = {})
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
        ReminderListPage()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReminderListPagePreviewDark() {
    BaseAppTheme {
        ReminderListPage()
    }
}
