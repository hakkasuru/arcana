package com.hakkasuru.arcana.debugprefs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakkasuru.arcana.ui.theme.Typography

@Composable
fun DocumentComposable(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.padding(8.dp).clickable { onClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Text(text = title, style = Typography.titleMedium)
            if(description.isNotEmpty()) Text(text = description)
        }
    }
}

@Preview(device = Devices.PIXEL_3A_XL)
@Composable
fun PreviewDocumentComposable() {
    DocumentComposable(
        "Login Arcana",
        "Login records are stored here"
    ) {
        // navigate to document preference screen
    }
}