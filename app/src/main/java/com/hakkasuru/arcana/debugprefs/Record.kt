package com.hakkasuru.arcana.debugprefs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakkasuru.arcana.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun RecordComposable(
    title: String,
    description: String,
    checked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(text = title, style = Typography.titleSmall)
            Text(text = description)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Box(modifier = Modifier.fillMaxWidth(1f)) {
            Switch(
                modifier = Modifier.align(Alignment.CenterEnd),
                checked = checked,
                onCheckedChange = {
                    scope.launch { onToggle(it) }
                }
            )
        }
    }
}

@Preview(device = Devices.PIXEL_3A_XL)
@Composable
fun PreviewRecordComposable() {
    val checkedState = remember { mutableStateOf(false) }
    RecordComposable(
        "Enable Greeting",
        "Show greeting to user on home screen",
        checkedState.value
    ) {
        checkedState.value = it
    }
}