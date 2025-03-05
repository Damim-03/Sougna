package com.example.sougna.presentation.view.TextFeild.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NameProfile(
    Name: String,
    onName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Name",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = Name,
            onValueChange = onName,
            placeholder = { Text("Enter Your Name") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}