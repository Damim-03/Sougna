package com.example.sougna.presentation.view.TextFeild.Profile

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
fun PhoneProfile(
    countryCode: String,
    onCountryCodeChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // List of some common country codes
    val countryCodes = listOf("+213", "+1", "+33", "+44", "+49", "+966")

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Phone Number",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            // Country Code Dropdown
            Box(modifier = Modifier.weight(0.3f)) {
                OutlinedButton(onClick = { expanded = true }) {
                    Text(text = countryCode)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    countryCodes.forEach { code ->
                        DropdownMenuItem(
                            text = { Text(code) },
                            onClick = {
                                onCountryCodeChange(code)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Phone Number Input
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { newValue ->
                    onPhoneNumberChange(newValue.filter { it.isDigit() }.take(10)) // Allow only digits, max 10
                },
                placeholder = { Text("Enter phone number") },
                modifier = Modifier.weight(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )
        }
    }
}
