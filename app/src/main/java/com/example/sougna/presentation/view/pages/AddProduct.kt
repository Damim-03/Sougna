package com.example.sougna.presentation.view.pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.sougna.presentation.viewmodel.ProductViewModel

@Composable
fun AddProductScreen(
    navController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel() // ✅ Use ProductViewModel
) {
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf("USD") }
    var selectedImages by remember { mutableStateOf<List<Uri?>>(List(3) { null }) }
    var selectedImageIndex by remember { mutableIntStateOf(0) }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImages = selectedImages.toMutableList().also { it[selectedImageIndex] = uri }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(120.dp)
                .background(Color(0xFFE57373), RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ProfileButton(onClick = { navController.navigate("profile") })
                AddProductButton(navController = navController)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            NameField(productName, { productName = it })
            Spacer(modifier = Modifier.height(14.dp))
            DescriptionField(productDescription, { productDescription = it })
            Spacer(modifier = Modifier.height(14.dp))
            PriceField(productPrice, { productPrice = it }, selectedCurrency, { selectedCurrency = it })
            Spacer(modifier = Modifier.height(25.dp))

            ImagePicker(selectedImages, onImageClick = { index ->
                selectedImageIndex = index
                imagePickerLauncher.launch("image/*")
            })

            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CancelButton(navController = navController)
                Spacer(modifier = Modifier.width(16.dp))
                AddButton(onClick = {
                    val priceValue = productPrice.toDoubleOrNull()
                    if (productName.isNotBlank() && priceValue != null) {
                        viewModel.addProduct(productName, productDescription, priceValue.toString(), selectedImages.firstOrNull()) {
                            navController.navigate("home")
                        }
                    }
                })
            }
        }
    }
}
