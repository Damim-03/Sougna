package com.example.sougna.presentation.view.pages

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sougna.presentation.view.TextFeild.Product.*
import com.example.sougna.presentation.view.buttons.ProductButton.*
import com.example.sougna.presentation.view.buttons.ProfileButton.ProfileButton
import com.example.sougna.presentation.view.components.CategoryDropdown
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AddProductScreen(
    navController: NavHostController,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.onEvent(AddProductEvent.ImageUrlChanged(it.toString())) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFFE57373), RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center, // Center the buttons
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileButton(navController = navController)
                Spacer(modifier = Modifier.width(16.dp)) // Add spacing between buttons
                AddProductButton(navController = navController)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            NameField(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(12.dp))

            DescriptionField(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(12.dp))

            PriceField(
                modifier = Modifier.fillMaxWidth(),
                productPrice = state.price.toString(),
                onPriceChange = { viewModel.onEvent(AddProductEvent.PriceChanged(it)) },
                selectedCurrency = "USD",
                onCurrencyChange = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            CategoryDropdown(
                selectedCategory = state.categoryId,
                onCategorySelected = { categoryId ->
                    viewModel.onEvent(AddProductEvent.CategorySelected(categoryId))
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            ImagePicker(
                selectedImages = listOf(Uri.parse(state.imageUrl)),
                onImageClick = { imagePickerLauncher.launch("image/*") }
            )

            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CancelButton(navController = navController)
                Spacer(modifier = Modifier.width(16.dp))
                AddButton(
                    onClick = {
                        viewModel.onEvent(AddProductEvent.Submit)
                    }
                )
            }

            if (state.isLoading) {
                Text(text = "Adding product...", modifier = Modifier.padding(16.dp))
            }

            state.error?.let {
                Text(text = "Error: $it", color = colorScheme.error, modifier = Modifier.padding(16.dp))
            }

            if (state.isSuccess) {
                LaunchedEffect(Unit) {
                    navController.navigate("home")
                }
            }
        }
    }
}
