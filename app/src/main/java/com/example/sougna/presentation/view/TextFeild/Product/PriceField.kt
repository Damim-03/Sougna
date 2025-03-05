package com.example.sougna.presentation.view.TextFeild.Product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceField(
    productPrice: String,
    onPriceChange: (String) -> Unit,
    selectedCurrency: String,
    onCurrencyChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currencies = listOf("USD", "EUR", "GBP", "DZD")
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Price of Product",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // Price input field
            OutlinedTextField(
                value = productPrice,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() || it == '.' }) { // Allow only numbers and decimal
                        onPriceChange(newValue)
                    }
                },
                placeholder = { Text("Enter Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )

            // Currency selection dropdown using ExposedDropdownMenuBox
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded } // Toggle menu visibility
            ) {
                OutlinedTextField(
                    value = selectedCurrency,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor() // Important for correct dropdown positioning
                        .width(100.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    currencies.forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(currency) },
                            onClick = {
                                onCurrencyChange(currency)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
