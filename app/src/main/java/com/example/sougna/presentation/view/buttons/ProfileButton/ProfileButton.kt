package com.example.sougna.presentation.view.buttons.ProfileButton

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        modifier = modifier
            .padding(top = 40.dp)
            .size(width = 140.dp, height = 45.dp)
    ) {
        Text("My Profile", color = Color.Black)
    }
}