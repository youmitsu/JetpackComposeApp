package com.example.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.ui.R

@Composable
fun SaveButton(isLoading: Boolean, onClick: () -> Unit) {
    if (isLoading) {
        return CircularProgressIndicator(
            modifier = Modifier.padding(end = 16.dp)
        )
    }
    Button(onClick = onClick) {
        Icon(
            Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.save)
        )
    }
}
