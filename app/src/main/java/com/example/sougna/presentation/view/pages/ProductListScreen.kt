import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sougna.presentation.view.components.ProductGrid
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@Composable
fun ProductListScreen(viewModel: AddProductViewModel = hiltViewModel()) {
    val products = viewModel.products // ✅ Directly use mutableStateListOf

    // Show products in a grid
    ProductGrid(products = products) // ✅ Pass the correct product list
}
