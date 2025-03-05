package com.example.sougna.presentation.view.TextFeild.Product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionField(
    productDescription: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Description of Product",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = productDescription,
            onValueChange = onDescriptionChange,
            placeholder = { Text("Enter Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), // Adjusted height for text area
            maxLines = 5, // Allows multiple lines
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
    }
}
