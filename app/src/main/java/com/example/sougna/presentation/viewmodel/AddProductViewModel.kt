package com.example.sougna.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.sougna.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor() : ViewModel() {
    var products = mutableStateListOf<Product>() // ✅ Holds product list
        private set

    fun addProduct(name: String, description: String, price: String, imageUri: Uri?) {
        val priceValue = price.toDoubleOrNull() ?: return
        val newProduct = Product(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            price = priceValue,
            thumbnailUrl = imageUri?.toString() ?: "",
            rating = 4.5
        )
        viewModelScope.launch {
            products.add(newProduct) // ✅ Notify UI of changes
        }
    }
}


