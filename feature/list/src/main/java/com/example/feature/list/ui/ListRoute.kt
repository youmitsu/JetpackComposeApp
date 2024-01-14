package com.example.feature.list.ui

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature.list.R
import com.example.navigation.Screen
import com.example.ui.theme.BaseAppTheme

@Composable
fun ListRoute(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel()
) {
    val uiState by listViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        listViewModel.onEditClickEvent.collect {
            navController.navigate(Screen.Edit.createRoute(it))
        }
    }

    DisposableEffect(Unit) {
        val listener: NavController.OnDestinationChangedListener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                if (destination.route == "home") {
                    // TODO: 更新があったときのみリロードする
                    listViewModel.refresh()
                }
            }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    ListScreen(
        onRefresh = listViewModel::refresh,
        onClickEdit = listViewModel::onClickEdit,
        uiState = uiState,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ListScreen(
    uiState: ListUiState,
    onRefresh: () -> Unit,
    onClickEdit: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = onRefresh
    )
    Box(modifier = modifier.pullRefresh(state)) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items = uiState.currentItems) { item ->
                Meigen(title = item.body, onClickEdit = { onClickEdit(item.id) })
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isRefreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

@Composable
private fun Meigen(title: String, onClickEdit: () -> Unit, modifier: Modifier = Modifier) {
    val expanded by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            IconButton(onClick = onClickEdit) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.edit)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BaseAppTheme {
        ListScreen(
            uiState = ListUiState(
                currentItems = listOf(
                    com.example.model.Meigen(
                        id = "1",
                        body = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello",
                        createdAt = java.util.Date()
                    ),
                    com.example.model.Meigen(
                        id = "2",
                        body = "World",
                        createdAt = java.util.Date()
                    ),
                )
            ),
            onRefresh = {},
            onClickEdit = {},
        )
    }
}
