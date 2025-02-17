package com.example.sougna.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sougna.data.model.Category
import com.example.sougna.data.model.Product
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.viewmodel.CategoryViewModel
import com.example.sougna.presentation.viewmodel.ProductViewModel


/**
 * Composable function that displays a list of categories
 * @param categories The list of categories to display
 * @param modifier Modifier for the layout
 */
@Composable
fun CategoryList(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display category icon
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Display category name
                Text(text = category.name)
            }
        }
    }
}

/**
 * Composable function that displays a list of products in card format
 * @param products The list of products to display
 * @param modifier Modifier for the layout
 */
@Composable
fun ProductList(
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(products) { product ->
            // Card container for each product
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Product image
                    AsyncImage(
                        model = product.thumbnailUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Product name
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Product description
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Product price
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

/**
 * Main screen composable that displays both categories and products
 * @param modifier Modifier for the layout
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onAddProductClick: () -> Unit,
    productViewModel: ProductViewModel
) {

    //inject viewModel
    //val productViewModel : ProductViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()


    // Collect state from ViewModels
    val productState by productViewModel.uiState.collectAsState()
    val categoryState by categoryViewModel.categoryState.collectAsState()

    Box(modifier = modifier.padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(16.dp))

            // Display category list
            CategoryList(
                categories = categoryState.categories,
                modifier = Modifier.fillMaxWidth()
            )

            // Handle product state
            when {
                productState.isLoading -> {
                    // Show loading indicator
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                productState.error != null -> {
                    // Show error message
                    Text(
                        text = "Error: ${productState.error}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                else -> {
                    // Display product list
                    ProductList(
                        products = productState.products,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = onAddProductClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Product")
        }
    }
}