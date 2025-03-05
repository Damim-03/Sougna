package com.example.sougna.presentation.view.TextFeild.Product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NameField(
    productName: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Name of Product",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = onNameChange,
            placeholder = { Text("Enter Name of Product") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}