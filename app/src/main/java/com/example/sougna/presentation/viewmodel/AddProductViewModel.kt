package com.example.sougna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sougna.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val addProductUseCase: AddProductUseCase) : ViewModel() {

    ///TODO: Add the viewModel logic to add a product
}