package com.example.sougna.presentation.view.TextFeild.Profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun EmailProfile(
    Email: String,
    onEmail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedDomain by remember { mutableStateOf("@outlook.com") }

    val domains = listOf("@gmail.com", "@outlook.com", "@hotmail.com")

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Email",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = Email,
            onValueChange = { newValue ->
                if (newValue.contains("@")) {
                    onEmail(newValue) // Allow manual domain entry
                } else {
                    onEmail(newValue) // Keep only the username part
                }
            },
            placeholder = { Text("Enter Email Address") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .clickable { expanded = true }
                        .padding(8.dp)
                ) {
                    Text(
                        text = selectedDomain,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    domains.forEach { domain ->
                        DropdownMenuItem(
                            text = { Text(domain) },
                            onClick = {
                                selectedDomain = domain
                                if (!Email.contains("@")) {
                                    onEmail(Email + domain) // Append domain if missing
                                }
                                expanded = false
                            }
                        )
                    }
                }
            }
        )
    }
}
