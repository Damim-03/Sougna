package com.example.sougna.presentation.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sougna.presentation.view.state.CategoryUiState
import com.example.sougna.presentation.view.state.ProductUiState
import com.example.sougna.presentation.viewmodel.AddProductViewModel
import com.example.sougna.presentation.viewmodel.CategoryState
import com.example.sougna.presentation.viewmodel.CategoryViewModel
import com.example.sougna.presentation.viewmodel.ProductViewModel

@Composable
fun HeroSection() {
    val productViewModel: ProductViewModel = hiltViewModel() // ✅ Use ProductViewModel
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    val uiState by productViewModel.uiState.collectAsStateWithLifecycle() // ✅ Observe StateFlow
    val categoriesState by categoryViewModel.categoryState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        CategoriesRow(categoriesState.categories)

        Spacer(modifier = Modifier.height(5.dp))

        ProductGrid(uiState.products) // ✅ Display products on the homepage
    }
}



